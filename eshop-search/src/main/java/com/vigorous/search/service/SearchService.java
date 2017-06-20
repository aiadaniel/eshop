package com.vigorous.search.service;

import com.vigorous.search.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String query,int page,int rows) throws Exception;
}
