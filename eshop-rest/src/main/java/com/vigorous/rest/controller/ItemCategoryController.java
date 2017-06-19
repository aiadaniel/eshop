package com.vigorous.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vigorous.common.utils.JsonUtils;
import com.vigorous.rest.pojo.CategoryResult;
import com.vigorous.rest.service.ItemCategoryService;

@Controller
public class ItemCategoryController {

	@Autowired
	private ItemCategoryService itemCategoryService;
	
	@RequestMapping(value="/itemcat/list",produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getCatList(String callback) {
		CategoryResult result = itemCategoryService.getItemCatList();
		
		String json = JsonUtils.objectToJson(result);
		
		//拼装返回值
		String res = callback + "(" + json + ");";
		return res;
	}
}
