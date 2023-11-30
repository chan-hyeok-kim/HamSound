package com.ham.gov.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Controller
@Slf4j
public class HomeController {

	@Value("${data.api.key}")
	private String apiKey;
	
	@RequestMapping("/")
	public String goHome(Model model) throws Exception{
		
		log.warn("루트 가는지 확인");
		
	  
	    
		return "index";
	}
	
	
	
	
}
