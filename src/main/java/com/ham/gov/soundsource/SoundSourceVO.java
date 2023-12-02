package com.ham.gov.soundsource;

import com.ham.gov.commons.CommonsVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SoundSourceVO extends CommonsVO {

	private Long soundSourceNo;
	private String soundSourcePath;
	private String soundSourceName;
	private String soundSourceOriName;
	private String downloadPath;
	private String license;
	private String description;
	
}
