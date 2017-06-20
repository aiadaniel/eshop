package com.vigorous.service;

import com.vigorous.common.pojo.DataGridResult;
import com.vigorous.common.pojo.ResultModel;
import com.vigorous.pojo.TbContent;

public interface ContentService {
	ResultModel insertContent(TbContent tbContent);
	DataGridResult getContentList(long catId, Integer page, Integer rows) throws Exception;
}
