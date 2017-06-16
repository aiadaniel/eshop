package com.vigorous.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vigorous.common.pojo.TreeNode;
import com.vigorous.mapper.TbItemCatMapper;
import com.vigorous.pojo.TbItemCat;
import com.vigorous.pojo.TbItemCatExample;
import com.vigorous.pojo.TbItemCatExample.Criteria;
import com.vigorous.service.ItemCategoryService;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

	@Autowired
	TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<TreeNode> getCategoryList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criterion = example.createCriteria();
		criterion.andParentIdEqualTo(parentId);
		
		List<TbItemCat> lists = tbItemCatMapper.selectByExample(example);
		
		List<TreeNode> nodes = new ArrayList<>();
		
		for (TbItemCat cat : lists) {
			TreeNode node = new TreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent() ? "close" : "open");
			nodes.add(node);
		}
		
		return nodes;
	}

}
