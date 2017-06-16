package com.vigorous.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vigorous.common.pojo.TreeNode;
import com.vigorous.service.ItemCategoryService;

@Controller
@RequestMapping("/item/cat")
public class ItemCategoryController {

	@Autowired
	ItemCategoryService itemCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<TreeNode> getCatList(@RequestParam(value="id",defaultValue="0")Long parendId) {
		return itemCategoryService.getCategoryList(parendId);
	}
}
