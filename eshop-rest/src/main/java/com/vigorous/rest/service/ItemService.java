package com.vigorous.rest.service;

import com.vigorous.common.pojo.ResultModel;

public interface ItemService {
	ResultModel getItemBaseInfo(long itemId);
	ResultModel getItemDesc(long itemId);
	ResultModel getItemParam(long itemId);
}
