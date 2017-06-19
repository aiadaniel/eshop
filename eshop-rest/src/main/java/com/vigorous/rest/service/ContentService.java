package com.vigorous.rest.service;

import java.util.List;

import com.vigorous.pojo.TbContent;

public interface ContentService {
	List<TbContent> getContentList(long contentCid);
}
