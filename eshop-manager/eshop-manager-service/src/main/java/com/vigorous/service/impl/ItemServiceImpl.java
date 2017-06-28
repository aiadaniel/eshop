package com.vigorous.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vigorous.common.pojo.DataGridResult;
import com.vigorous.common.pojo.ResultModel;
import com.vigorous.common.utils.IDUtils;
import com.vigorous.mapper.TbItemDescMapper;
import com.vigorous.mapper.TbItemMapper;
import com.vigorous.mapper.TbItemParamItemMapper;
import com.vigorous.pojo.TbItem;
import com.vigorous.pojo.TbItemDesc;
import com.vigorous.pojo.TbItemExample;
import com.vigorous.pojo.TbItemExample.Criteria;
import com.vigorous.pojo.TbItemParamItem;
import com.vigorous.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public TbItem geItemById(long itemId) {
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> lists = itemMapper.selectByExample(example);
		if (lists != null && lists.size() > 0) {
			TbItem item = lists.get(0);
			return item;
		}
		return null;
	}
	
	@Override
	public DataGridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		
		PageHelper.startPage(page, rows);
		
		List<TbItem> lists = itemMapper.selectByExample(example);
		
		DataGridResult result = new DataGridResult();
		result.setRows(lists);
		
		PageInfo<TbItem> pageInfo = new PageInfo<>(lists);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public ResultModel createItem(TbItem item, String desc, String itemParam) throws Exception {
		//item补全
		//生成商品ID
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		// '商品状态，1-正常，2-下架，3-删除',
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//插入到数据库
		itemMapper.insert(item);
		//添加商品描述信息
		ResultModel result = insertItemDesc(itemId, desc);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		//添加规格参数
		result = insertItemParamItem(itemId, itemParam);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		
		//通过mq通知其他服务更新数据
		sendMsgToMQ(itemId, "insert");
		
		return ResultModel.ok();
	}
	/**
	 * 添加商品描述
	 * <p>Title: insertItemDesc</p>
	 * <p>Description: </p>
	 * @param desc
	 */
	private ResultModel insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return ResultModel.ok();
	}
	
	/**
	 * 添加规格参数
	 * <p>Title: insertItemParamItem</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @param itemParam
	 * @return
	 */
	private ResultModel insertItemParamItem(Long itemId, String itemParam) {
		//创建一个pojo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		//向表中插入数据
		itemParamItemMapper.insert(itemParamItem);
		
		return ResultModel.ok();
		
	}
	
	private void sendMsgToMQ(long itemId,String type) {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("itemId", itemId);
			map.put("type", type);
			map.put("data", System.currentTimeMillis());
			rabbitTemplate.convertAndSend("item."+type,MAPPER.writeValueAsString(map));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
