package com.ham.gov.soundsource;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SourceReturnVO {

	private String count;
	private String next;
	private List<Object> results;
	private String previous;
}
