package com.vigorous.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.pojo.TbUser;

public interface UserService {
	ResultModel checkData(String content,Integer type);
	ResultModel createUser(TbUser user);
	ResultModel userLogin(String username,String password,HttpServletRequest request,HttpServletResponse res);
	ResultModel getUserByToken(String token);
}
