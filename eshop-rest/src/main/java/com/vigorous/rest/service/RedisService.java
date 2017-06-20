package com.vigorous.rest.service;

import com.vigorous.common.pojo.ResultModel;

public interface RedisService {
	ResultModel syncContent(long contentCid);
}
