package com.vigorous.home.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.common.utils.HttpClientUtil;
import com.vigorous.common.utils.JsonUtils;
import com.vigorous.home.service.ContentService;
import com.vigorous.pojo.TbContent;

@Service
public class ContentServiceImpl implements ContentService {

	// 首页url
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	// 首页广告栏url
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String getContentList() {
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		System.out.println("==index ad res " + result);
		try {
			ResultModel resultModel = ResultModel.formatToList(result, TbContent.class);
			List<TbContent> lists = (List<TbContent>) resultModel.getData();
			List<Map> resultLists = new ArrayList<>();
			for (TbContent tbContent : lists) {
				Map map = new HashMap<>();
				map.put("src", tbContent.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", tbContent.getPic2());
				map.put("heightB", 240);
				map.put("widthB", 550);
				map.put("href", tbContent.getUrl());
				map.put("alt", tbContent.getSubTitle());
				resultLists.add(map);
			}
			return JsonUtils.objectToJson(resultLists);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
