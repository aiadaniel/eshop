package com.vigorous.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.common.utils.ExceptionUtil;
import com.vigorous.rest.dao.JedisClient;
import com.vigorous.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public ResultModel syncContent(long contentCid) {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid+"");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultModel(500,ExceptionUtil.getStackTrace(e));
		}
		return ResultModel.ok();
	}

}
