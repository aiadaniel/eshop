package com.vigorous.home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.home.service.UserService;

//@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/*
	 * 目前暂时不用前端调用sso系统的方式，而是直接登录sso后写cookie
	 */
	@RequestMapping("/dologin")
	@ResponseBody
	public ResultModel doLogin(String username, String password, 
			HttpServletRequest request, HttpServletResponse response) {
		return userService.login(username, password, request, response);
	}
}
