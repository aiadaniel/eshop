package com.vigorous.home.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.common.utils.HttpClientUtil;
import com.vigorous.common.utils.JsonUtils;
import com.vigorous.home.pojo.Order;
import com.vigorous.home.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	

	@Override
	public String createOrder(Order order) {
		//调用taotao-order的服务提交订单。
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		System.out.println("==home get order: " + json);
		//把json转换成taotaoResult
		ResultModel taotaoResult = ResultModel.format(json);
		if (taotaoResult.getStatus() == 200) {
			//Long orderId = (Long) taotaoResult.getData();// java.lang.Integer cannot be cast to java.lang.Long
			
			//or Object orderId = taotaoResult.getData();
			
			return String.valueOf(taotaoResult.getData());
		}
		return "";
	}

}
