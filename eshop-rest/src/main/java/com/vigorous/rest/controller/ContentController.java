package com.vigorous.rest.controller;

import java.util.List;

import org.apache.commons.codec.language.bm.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.common.utils.ExceptionUtil;
import com.vigorous.pojo.TbContent;
import com.vigorous.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{contentCid}")
	@ResponseBody
	public ResultModel getContentList(@PathVariable Long contentCid) {
		try {
			List<TbContent> list = contentService.getContentList(contentCid);
			return ResultModel.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultModel(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
