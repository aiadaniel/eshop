package com.vigorous.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.common.utils.ExceptionUtil;
import com.vigorous.pojo.TbUser;
import com.vigorous.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {

		ResultModel result = null;

		// 参数有效性校验
		if (StringUtils.isBlank(param)) {
			result = new ResultModel(400, "校验内容不能为空");
		}
		if (type == null) {
			result = new ResultModel(400, "校验内容类型不能为空");
		}
		if (type != 1 && type != 2 && type != 3) {
			result = new ResultModel(400, "校验内容类型错误");
		}
		// 校验出错
		if (null != result) {
			if (null != callback) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			} else {
				return result;
			}
		}
		// 调用服务
		try {
			result = userService.checkData(param, type);

		} catch (Exception e) {
			result = new ResultModel(500, ExceptionUtil.getStackTrace(e));
		}

		if (null != callback) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		} else {
			return result;
		}
	}

	@PostMapping("/register")
	@ResponseBody
	public ResultModel createUser(TbUser user) {

		try {
			ResultModel result = userService.createUser(user);
			return result;
		} catch (Exception e) {
			return new ResultModel(500, ExceptionUtil.getStackTrace(e));
		}
	}

	@PostMapping(value = "/login")
	@ResponseBody
	public ResultModel userLogin(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			ResultModel result = userService.userLogin(username, password, request, response);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultModel(500, ExceptionUtil.getStackTrace(e));
		}
	}

	@PostMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		ResultModel result = null;
		try {
			result = userService.getUserByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultModel(500, ExceptionUtil.getStackTrace(e));
		}

		// 判断是否为jsonp调用
		if (StringUtils.isBlank(callback)) {
			return result;
		} else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}

	}
}