package com.vigorous.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PicturService {
	Map uploadPic(MultipartFile file);
}
