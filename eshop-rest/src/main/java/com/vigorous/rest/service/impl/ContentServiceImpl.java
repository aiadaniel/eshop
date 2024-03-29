package com.vigorous.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vigorous.common.utils.JsonUtils;
import com.vigorous.mapper.TbContentMapper;
import com.vigorous.pojo.TbContent;
import com.vigorous.pojo.TbContentExample;
import com.vigorous.pojo.TbContentExample.Criteria;
import com.vigorous.rest.dao.JedisClient;
import com.vigorous.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public List<TbContent> getContentList(long contentCid) {
		// 从缓存中取内容
		try {
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid + "");
			if (!StringUtils.isBlank(result)) {
				// 把字符串转换成list
				List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		List<TbContent> lists = contentMapper.selectByExample(example);
		System.out.println("==tb content size " + lists.size());

		// 向缓存中添加内容
		try {
			// 把list转换成字符串
			String cacheString = JsonUtils.objectToJson(lists);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid + "", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lists;
	}

}
