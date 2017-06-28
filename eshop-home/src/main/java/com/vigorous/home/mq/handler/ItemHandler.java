package com.vigorous.home.mq.handler;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vigorous.home.service.ItemService;

public class ItemHandler {
	
//	@Autowired
//	private RedisService redisService;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	//TODO 
	public void execute(String msg) {
		// 删除缓存中的数据
		try {
			JsonNode jsonNode = MAPPER.readTree(msg);
			Long itemId = jsonNode.get("itemId").asLong();
            String type = jsonNode.get("type").asText();
            Object data = jsonNode.get("data");
			System.out.println("==get mq msg " + itemId + ":" + type + ":" + data);
//			String key = ItemService.REDIS_KEY + jsonNode.get("itemId").asLong();
//			this.redisService.del(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
