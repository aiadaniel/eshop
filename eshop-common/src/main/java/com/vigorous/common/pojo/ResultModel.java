package com.vigorous.common.pojo;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultModel {
	public static final String OK = "OK";
	private int status;

	private String msg;

	private Object data;

	public int getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	public ResultModel(int code, String message) {
		this.status = code;
		this.msg = message;
		this.data = "";
	}

	public ResultModel(int code, String message, Object content) {
		this.status = code;
		this.msg = message;
		this.data = content;
	}

	public static ResultModel ok() {
		return new ResultModel(200, OK);
	}

	public static ResultModel ok(Object data) {
		return new ResultModel(200, OK, data);
	}

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static ResultModel formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return new ResultModel(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(),obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
    public static ResultModel formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ResultModel.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return new ResultModel(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
