package com.ham.gov.commons;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SoundKeyEqValue {

	public Map getValueByKey(String key, Object value, String target) {
		 if(key.equals(target)) {
			 Map<String,Object> map=new HashMap<String, Object>();
			 map.put(key, value.toString());
			 
			 return map;
		 }
		 
		 return null;
	}
	
}
