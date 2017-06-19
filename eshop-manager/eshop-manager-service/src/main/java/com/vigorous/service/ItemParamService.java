package com.vigorous.service;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.pojo.TbItemParam;

public interface ItemParamService {
	ResultModel getItemParamByCid(long cid);
	ResultModel insertItemParam(TbItemParam itemParam);
}
