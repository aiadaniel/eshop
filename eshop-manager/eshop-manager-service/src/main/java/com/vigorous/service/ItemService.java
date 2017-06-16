package com.vigorous.service;

import com.vigorous.common.pojo.DataGridResult;
import com.vigorous.common.pojo.ResultModel;
import com.vigorous.pojo.TbItem;

public interface ItemService {
	TbItem geItemById(long itemId);

	DataGridResult getItemList(int page, int rows);
	
	ResultModel createItem(TbItem item,String desc,String itemParam) throws Exception;

}
