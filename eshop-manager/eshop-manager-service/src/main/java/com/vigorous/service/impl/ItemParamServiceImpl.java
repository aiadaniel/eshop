package com.vigorous.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.mapper.TbItemParamMapper;
import com.vigorous.pojo.TbItemParam;
import com.vigorous.pojo.TbItemParamExample;
import com.vigorous.pojo.TbItemParamExample.Criteria;
import com.vigorous.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public ResultModel getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		
		List<TbItemParam> lists = itemParamMapper.selectByExampleWithBLOBs(example);
		if (lists != null && lists.size() > 0) {
			return ResultModel.ok(lists.get(0));
		}
		return ResultModel.ok();
	}

	@Override
	public ResultModel insertItemParam(TbItemParam itemParam) {
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		
		itemParamMapper.insert(itemParam);
		return ResultModel.ok();
	}

}
