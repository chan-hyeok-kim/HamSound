package com.ham.gov.soundsource.VOs;

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
}
