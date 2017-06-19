package com.vigorous.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vigorous.mapper.TbItemCatMapper;
import com.vigorous.pojo.TbItemCat;
import com.vigorous.pojo.TbItemCatExample;
import com.vigorous.pojo.TbItemCatExample.Criteria;
import com.vigorous.rest.pojo.CategoryNode;
import com.vigorous.rest.pojo.CategoryResult;
import com.vigorous.rest.service.ItemCategoryService;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public CategoryResult getItemCatList() {
		CategoryResult result = new CategoryResult();
		result.setData(getCatList(0));
		return result;
	}

	@SuppressWarnings("unchecked")
	private List<?> getCatList(long parentId) {
		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		// 返回值list
		List resultList = new ArrayList<>();
		// 向list中添加节点
		for (TbItemCat tbItemCat : list) {
			// 判断是否为父节点
			if (tbItemCat.getIsParent()) {
				CategoryNode catNode = new CategoryNode();
				if (parentId == 0) {
					catNode.setName(
							"<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/" + tbItemCat.getId() + ".html");
				catNode.setItem(getCatList(tbItemCat.getId()));

				resultList.add(catNode);
				// 如果是叶子节点
			} else {
				resultList.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
			}
		}
		return resultList;
	}

}
