package com.ham.gov.soundsource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/sound/*")
@Slf4j
public class SoundSourceController {

	@Value("${data.api.key}")
	private String apiKey;
	
	@GetMapping("ajaxList")
	public Flux<SourceReturnVO> getAjaxList()throws Exception{
		
		
		
	  WebClient webClient=WebClient.create("https://freesound.org/apiv2/search/text/?token="+apiKey);
	    
	    Flux<SourceReturnVO> results=webClient.get()
	             .uri("")
	             .retrieve()
	             .bodyToFlux(SourceReturnVO.class);
		
	    log.warn("음원 오는지 확인 {}",results);
	    
		return results;
	}
	
}
