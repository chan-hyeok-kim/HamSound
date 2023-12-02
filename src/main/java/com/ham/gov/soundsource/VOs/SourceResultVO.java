package com.ham.gov.soundsource.VOs;

import java.util.LinkedHashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SourceResultVO {

	private String id;
	private String name;
	private List<Object> tags; 
	private String license;
	private String username;
	private Object previews;
	private String download;
	private String description;
	
	public SourceResultVO() {
		// TODO Auto-generated constructor stub
	}
	
	public SourceResultVO(String id, String name, String download, String description,String license, Object previews) {
		this.id=id;
		this.name=name;
		this.download=download;
		this.description=description;
		this.previews=previews;
	}
	
	
	
}
