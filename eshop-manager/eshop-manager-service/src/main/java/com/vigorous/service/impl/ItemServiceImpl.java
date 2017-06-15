package com.vigorous.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vigorous.mapper.TbItemMapper;
import com.vigorous.pojo.TbItem;
import com.vigorous.pojo.TbItemExample;
import com.vigorous.pojo.TbItemExample.Criteria;
import com.vigorous.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;

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

}
