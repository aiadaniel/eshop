package com.vigorous.rest.controller;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.rest.service.RedisService;

@Controller
@RequestMapping("/cache/sync")
public class RedisController {
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/content/{contentCid}")
	@ResponseBody
	public ResultModel contentCacheSync(@PathVariable Long contentCid) {
		return redisService.syncContent(contentCid);
	}
}
