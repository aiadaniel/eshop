package com.vigorous.service;

import java.util.List;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.common.pojo.TreeNode;

public interface ContentCategoryService {
	List<TreeNode> getCategoryList(long parentId);
	ResultModel insertContentCategory(long parentId,String name);
}
