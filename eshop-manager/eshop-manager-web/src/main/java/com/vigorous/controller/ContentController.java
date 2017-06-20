package com.vigorous.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vigorous.common.pojo.DataGridResult;
import com.vigorous.common.pojo.ResultModel;
import com.vigorous.pojo.TbContent;
import com.vigorous.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public ResultModel insertContent(TbContent content) {
		return contentService.insertContent(content);
	}
	
	@RequestMapping("/query/list")
	@ResponseBody
	public DataGridResult getContentList(Long categoryId, Integer page, Integer rows) throws Exception {
		return contentService.getContentList(categoryId, page, rows);
	}
	
}
