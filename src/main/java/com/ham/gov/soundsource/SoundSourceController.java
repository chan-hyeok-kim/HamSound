package com.ham.gov.soundsource;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.ham.gov.soundsource.VOs.SourceResultVO;
import com.ham.gov.soundsource.VOs.SourceReturnVO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/sound/*")
@Slf4j
public class SoundSourceController {

	@Value("${data.api.key}")
	private String apiKey;
	
	@GetMapping("ajaxList")
	public Flux<Object> getAjaxList() {
	    WebClient webClient = WebClient.create("https://freesound.org/apiv2/search/text/?token=" + apiKey);

	    return webClient.get()
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
	}
	
//	@GetMapping("ajaxList")
//	public Flux<SourceReturnVO> getAjaxList()throws Exception{
//		
//		
//		
//	  WebClient webClient=WebClient.create("https://freesound.org/apiv2/search/text/?token="+apiKey);
//	    
//	    Flux<SourceReturnVO> results=webClient.get()
//	             .uri("")
//	             .retrieve()
//	             .bodyToFlux(SourceReturnVO.class);
//		
//	    
//	    List<String> ids=new ArrayList<String>();
//	    
//	    results.subscribe((s)->{
//			SourceReturnVO sourceReturnVOs = s;
//			log.warn("results : {} ", sourceReturnVOs.getResults());
//			
//			if(sourceReturnVOs.getResults().size()==0) {
//				return;
//			}
//			List<Object> srResults=sourceReturnVOs.getResults();
//            
//			for(Object r: srResults) {
//			  LinkedHashMap<String, Object> sr=(LinkedHashMap)r;
//			  log.warn("링크드해쉬맵{}",sr);
//			  ids.add(sr.get("id").toString());
//			}
//			
//		});
//	    
//	    
//	    
//	   for(String e: ids) {
//	    WebClient soundInstance=WebClient.builder()
//	    		                      .baseUrl("https://freesound.org/apiv2/sounds/"+e+"/")
//	    		                      .build();
//	    
//	    Map<String, Object> map=new HashMap<String, Object>();
//	    
//	    map.put("token",apiKey);
//	 
//	    Flux<Object> Instances=soundInstance.get()
//	                 .uri(apiKey, map)
//	                 .retrieve()
//	                 .bodyToFlux(Object.class);
//	    log.warn("오는지만일단 확인{}",Instances.collectList().block());
//	    }
//	    
//	    
//	    
//	    
//	    
//	  
//		return results;
//	}
	
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
