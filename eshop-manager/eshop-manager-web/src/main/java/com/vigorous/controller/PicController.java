package com.vigorous.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vigorous.common.pojo.PictureResult;
import com.vigorous.service.PicturService;

@Controller
public class PicController {

	@Autowired
	private PicturService picturService;
	
	@RequestMapping(value="/pic/upload",method=RequestMethod.POST)
	@ResponseBody
	public PictureResult  pictureUpload(MultipartFile uploadFile) {//NOTE: 有个坑，参数名要跟js的参数一样，否则取不到file
		return picturService.uploadPic(uploadFile);
	}
}
