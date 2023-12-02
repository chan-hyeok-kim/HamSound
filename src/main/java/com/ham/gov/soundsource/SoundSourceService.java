package com.ham.gov.soundsource;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SoundSourceService{
	
	@Autowired
	private SoundSourceDAO soundSourceDAO;

	public int setAdd(SoundSourceVO soundSourceVO) throws Exception{
		return soundSourceDAO.setAdd(soundSourceVO);
	}
	
}
