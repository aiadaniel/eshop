package com.vigorous.home.service;

import com.vigorous.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(Long itemId);
	String getItemDescById(Long itemId);
	String getItemParam(Long itemId);
}
