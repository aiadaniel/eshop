package com.vigorous.service;

import java.util.List;

import com.vigorous.common.pojo.TreeNode;

public interface ItemCategoryService {
	List<TreeNode> getCategoryList(long parentId);
}
