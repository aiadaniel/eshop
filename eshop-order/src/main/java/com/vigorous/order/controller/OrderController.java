package com.vigorous.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.common.utils.ExceptionUtil;
import com.vigorous.order.pojo.Order;
import com.vigorous.order.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/create")
	@ResponseBody
	public ResultModel createOrder(@RequestBody Order order) {
		try {
			return orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultModel(500, ExceptionUtil.getStackTrace(e));
		}
	}
}