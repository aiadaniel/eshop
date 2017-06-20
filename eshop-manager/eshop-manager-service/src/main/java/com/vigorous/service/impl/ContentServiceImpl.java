package com.vigorous.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vigorous.common.pojo.DataGridResult;
import com.vigorous.common.pojo.ResultModel;
import com.vigorous.mapper.TbContentMapper;
import com.vigorous.pojo.TbContent;
import com.vigorous.pojo.TbContentExample;
import com.vigorous.pojo.TbContentExample.Criteria;
import com.vigorous.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper  contentMapper;
	
	@Override
	public ResultModel insertContent(TbContent content) {
		//补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		
		return ResultModel.ok();
	}
	
	@Override
	public DataGridResult getContentList(long catId, Integer page, Integer rows) throws Exception {
		//根据category_id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catId);
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		DataGridResult result = new DataGridResult(pageInfo.getTotal(), list);
		return result;
	}

}
