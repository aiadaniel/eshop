package com.vigorous.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vigorous.home.service.ContentService;

@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;

	@RequestMapping("/index")
	public String showindex(Model model) {
		String adJson = contentService.getContentList();
		model.addAttribute("ad1",adJson);
		return "index";
	}
}
