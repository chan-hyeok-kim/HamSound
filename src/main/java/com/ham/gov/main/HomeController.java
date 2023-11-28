package com.ham.gov.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@RequestMapping("/")
	public String goHome() throws Exception{
		
		log.warn("루트 가는지 확인");
		
		return "index";
	}
}
