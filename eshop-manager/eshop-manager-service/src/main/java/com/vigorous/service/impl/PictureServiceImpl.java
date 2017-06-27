package com.vigorous.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vigorous.common.pojo.PictureResult;
import com.vigorous.common.utils.ExceptionUtil;
import com.vigorous.common.utils.FtpUtil;
import com.vigorous.common.utils.IDUtils;
import com.vigorous.service.PicturService;

@Service
public class PictureServiceImpl implements PicturService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;

	@Value("${FTP_PORT}")
	private Integer FTP_PORT;

	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;

	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;

	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;

	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	@Override
	public PictureResult uploadPic(MultipartFile uploadFile) {
		// 判断上传图片是否为空
		if (null == uploadFile || uploadFile.isEmpty()) {
			return PictureResult.error("上传图片为空");
		}
		// 生成一个新的文件名
		// 取原始文件名
		String oldName = uploadFile.getOriginalFilename();
		String ext = oldName.substring(oldName.lastIndexOf("."));
		// 生成新文件名
		// UUID.randomUUID();
		String newName = IDUtils.genImageName();
		newName = newName + oldName.substring(oldName.lastIndexOf("."));
		// 图片上传
		String imagePath = new DateTime().toString("/yyyy/MM/dd");
		System.out.println("==upload pic path:" + imagePath + "  =name:" + newName);
		try {
			Boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, imagePath, newName,
					uploadFile.getInputStream());
			if (!result) {
				System.out.println("==ftp upload failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return PictureResult.error(ExceptionUtil.getStackTrace(e));
		}
		return PictureResult.ok(IMAGE_BASE_URL + imagePath + "/" + newName /*+ ext*/);
	}

}
