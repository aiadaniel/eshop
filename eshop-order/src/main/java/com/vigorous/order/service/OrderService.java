package com.vigorous.order.service;

import java.util.List;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.pojo.TbOrder;
import com.vigorous.pojo.TbOrderItem;
import com.vigorous.pojo.TbOrderShipping;

public interface OrderService {
	ResultModel createOrder(TbOrder order,List<TbOrderItem> items,TbOrderShipping shipping);
}
