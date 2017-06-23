package com.vigorous.service;

import org.springframework.web.multipart.MultipartFile;

import com.vigorous.common.pojo.PictureResult;

public interface PicturService {
	PictureResult uploadPic(MultipartFile file);
}
