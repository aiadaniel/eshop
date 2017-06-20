package com.vigorous.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vigorous.common.pojo.ResultModel;
import com.vigorous.common.pojo.TreeNode;
import com.vigorous.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
    @ResponseBody
	public List<TreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		return contentCategoryService.getCategoryList(parentId);
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public ResultModel createContentCategory(Long parentId,String name) {
		return contentCategoryService.insertContentCategory(parentId, name);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResultModel deleteContentCategory(Long parantId,Long id) {
		return ResultModel.ok();//TODO:
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ResultModel updateContentCategory(Long parantId,String name) {
		return ResultModel.ok();//TODO:
	}
}
