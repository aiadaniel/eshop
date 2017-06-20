package com.vigorous.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.vigorous.common.pojo.ResultModel;
import com.vigorous.common.utils.ExceptionUtil;
import com.vigorous.search.pojo.SearchResult;
import com.vigorous.search.service.SearchService;

@Controller

public class SearchController {
	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "60") Integer rows) {
		System.out.println("==on search " + queryString);
		// 查询条件不能为空
		if (StringUtils.isBlank(queryString)) {
			return new ResultModel(400, "查询条件不能为空");
		}
		SearchResult searchResult = null;
		try {
			queryString = new String(queryString.getBytes("iso8859-1"),"utf-8");
			System.out.println("==on search encode " + queryString);
			searchResult = searchService.search(queryString, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultModel(500, ExceptionUtil.getStackTrace(e));
		}
		return ResultModel.ok(searchResult);

	}
}
