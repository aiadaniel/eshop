package com.vigorous.home.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.home.pojo.CartItem;

public interface CartService {
	ResultModel addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
	ResultModel deleteCartItem(long itemId,HttpServletRequest request,HttpServletResponse response);
	List<CartItem> getCartItemList(HttpServletRequest request,HttpServletResponse response);
	ResultModel changeItemNum(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
}
