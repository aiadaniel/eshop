package com.vigorous.home.service;

import com.vigorous.home.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String query,int page);
}
