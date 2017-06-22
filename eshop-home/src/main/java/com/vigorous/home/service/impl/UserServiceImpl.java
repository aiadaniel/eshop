package com.vigorous.home.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.common.utils.CookieUtils;
import com.vigorous.common.utils.HttpClientUtil;
import com.vigorous.home.service.UserService;
import com.vigorous.pojo.TbUser;

@Service
public class UserServiceImpl implements UserService {

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;

	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;

	@Value("${SSO_USER_LOGIN}")
	public String SSO_USER_LOGIN;
	
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;
	

	@Override
	public TbUser getUserByToken(String token) {
		try {
			if (token == null) {
				System.out.println("==try get user but token null");
				return null;
			}
			// 调用sso系统的服务，根据token取用户信息
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);//TODO: 同步调用，需要异步吗
			System.out.println("==try get user " + json + " token:" + token);
			if (json == null || "".equals(json)) {
				System.out.println("==try get user by token but empty");
				return null;
			}
			// 把json转换成TaotaoREsult
			ResultModel result = ResultModel.formatToPojo(json, TbUser.class);
			if (result.getStatus() == 200) {
				TbUser user = (TbUser) result.getData();
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultModel login(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		// 请求参数
		Map<String, String> param = new HashMap<>();
		param.put("username", username);
		param.put("password", password);
		// 登录处理
		String stringResult = HttpClientUtil.doPost(SSO_BASE_URL + SSO_USER_LOGIN, param);
		ResultModel result = ResultModel.format(stringResult);
		// 登录出错
		if (result.getStatus() != 200) {
			return result;
		}
		// 登录成功后把取token信息，并写入cookie
		String token = (String) result.getData();
		CookieUtils.setCookie(request, response, "SSO_TOKEN", token);
		System.out.println("==sso set cookie: " + token);
		
		return null;
	}

}
