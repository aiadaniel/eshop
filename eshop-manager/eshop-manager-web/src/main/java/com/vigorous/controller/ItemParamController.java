package com.vigorous.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.pojo.TbItemParam;
import com.vigorous.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{catid}")
	@ResponseBody
	public ResultModel getItemParamByCid(@PathVariable Long catid) {
		return itemParamService.getItemParamByCid(catid);
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public ResultModel insertItemParam(@PathVariable Long cid, String paramData) {
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		return itemParamService.insertItemParam(itemParam);
	}
}
