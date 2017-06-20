package com.vigorous.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.rest.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public ResultModel getItemBaseInfo(@PathVariable Long itemId) {
		return itemService.getItemBaseInfo(itemId);
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public ResultModel getItemDesc(@PathVariable Long itemId) {
		return itemService.getItemDesc(itemId);
	}
	
	@ResponseBody
	public ResultModel getItemParam(@PathVariable Long itemId) {
		return itemService.getItemDesc(itemId);
	}
}