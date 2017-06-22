package com.vigorous.home.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.pojo.TbUser;

public interface UserService {
	TbUser getUserByToken(String token);
	ResultModel login(String username,String password,HttpServletRequest request,HttpServletResponse response);
}
