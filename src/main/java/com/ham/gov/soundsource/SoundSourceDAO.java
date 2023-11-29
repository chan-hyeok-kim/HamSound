package com.ham.gov.soundsource;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SoundSourceDAO {

	public int setAdd(SoundSourceVO soundSourceVO) throws Exception;
		
	
}
