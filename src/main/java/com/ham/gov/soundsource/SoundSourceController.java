package com.ham.gov.soundsource;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ham.gov.commons.SoundKeyEqValue;
import com.ham.gov.soundsource.VOs.SourceResultVO;
import com.ham.gov.soundsource.VOs.SourceReturnVO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/sound/*")
@Slf4j
public class SoundSourceController{

	@Value("${data.api.key}")
	private String apiKey;
	
	@Autowired
	private SoundKeyEqValue soundKeyEqValue;
	
	@GetMapping("ajaxList")
	public Flux<Object> getAjaxList() {
	    WebClient webClient = WebClient.create("https://freesound.org/apiv2/search/text/?token=" + apiKey);

	    Flux<Object> data=webClient.get()
	            .uri("")
	            .retrieve()
	            .bodyToFlux(SourceReturnVO.class)
	            .flatMap(sourceReturnVO -> {
	                List<String> ids = sourceReturnVO.getResults().stream()
	                        .filter(result -> result instanceof LinkedHashMap)
	                        .map(result -> ((LinkedHashMap<String, Object>) result).get("id").toString())
	                        .collect(Collectors.toList());

	                return Flux.fromIterable(ids)
	                        .parallel()
	                        .runOn(Schedulers.parallel())
	                        .flatMap(id -> {
	                            WebClient soundInstance = WebClient.builder()
	                                    .baseUrl("https://freesound.org/apiv2/sounds/" + id + "/")
	                                    .build();

	                       
	                            return soundInstance.get()
	                                    .uri("?token="+apiKey)
	                                    .retrieve()
	                                    .bodyToFlux(Object.class);
	                        })
	                        .sequential(); // To switch back to a sequential execution mode if necessary
	            });
	    
	    
	 
	    data.subscribe(item->{
	    	
	    	
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	try {
				String json=objectMapper.writeValueAsString(item);
			 LinkedHashMap<String, Object> obj=objectMapper.readValue(json, LinkedHashMap.class);
				
			 //
//			 obj.entrySet();
//			 log.warn("엔트리셋{}",obj.entrySet());
		
			 
			 ModelMapper modelMapper=new ModelMapper();
			 SourceResultVO resultVO=modelMapper.map(obj,SourceResultVO.class);

			 log.warn("모델매펍{}",resultVO);
			 
			 
//			 for(SourceResultVO o:obj) {
//				SoundSourceVO soundSourceVO=new SoundSourceVO();
//				soundSourceVO.setSoundSourceNo(Long.parseLong(o.getId()));
//				soundSourceVO.setSoundSourceName(o.getName());
//				soundSourceVO.setSoundSourcePath(o.getPreviews());
//				soundSourceVO.setDownloadPath(o.getDownload());
//				soundSourceVO.setLicense(o.getLicense());
//				soundSourceVO.setDescription(o.getDescription());
//				log.warn("인서트잘오는지실험{}",soundSourceVO);
//			 }
			 
				 
				 
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
	    	
	    	
	    },error->{
	    	log.warn("에러");
	    }, () -> {
	        // 완료 시 처리
	        System.out.println("Processing completed.");
	    });
	    
	    return data;
	}
	

	
	@GetMapping("sourceList")
	public Flux<SourceReturnVO> getSourceList()throws Exception{
		  //음원 파일
	    WebClient getSourceClient= WebClient.create("https://freesound.org/apiv2/search/content/?target=1234&token="+apiKey);
	    
		  Flux<SourceReturnVO> sourceList=getSourceClient.get()
		                  .uri("")
		                  .retrieve()
		                  .bodyToFlux(SourceReturnVO.class);
		    
		  log.warn("음원파일정보{}",sourceList);
		  
	    return sourceList;
	}		
	
	@PostMapping("sourceDown")
	public void getDown() throws Exception{
		
	}
}
