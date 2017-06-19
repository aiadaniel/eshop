package com.vigorous.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vigorous.mapper.TbContentMapper;
import com.vigorous.pojo.TbContent;
import com.vigorous.pojo.TbContentExample;
import com.vigorous.pojo.TbContentExample.Criteria;
import com.vigorous.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public List<TbContent> getContentList(long contentCid) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		List<TbContent> lists = contentMapper.selectByExample(example);
		System.out.println("==tb content size " + lists.size());
		return lists;
	}

}
