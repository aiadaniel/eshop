package com.vigorous.home.service;

import com.vigorous.pojo.TbUser;

public interface UserService {
	TbUser getUserByToken(String token);
}
