package com.vigorous.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 用于返回给前端js展示商品子分类
 */
public class CategoryNode {

	@JsonProperty("n")
	private String name;
	
	@JsonProperty("u")
	private String url;
	
	@JsonProperty("i")
	private List<?> item;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<?> getItem() {
		return item;
	}

	public void setItem(List<?> item) {
		this.item = item;
	}
	
}
