package com.vigorous.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vigorous.common.utils.JsonUtils;
import com.vigorous.service.PicturService;

@Controller
public class PicController {

	@Autowired
	private PicturService picturService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String pictureUpload(MultipartFile file) {
		Map result = picturService.uploadPic(file);
		return JsonUtils.objectToJson(result);
	}
}
