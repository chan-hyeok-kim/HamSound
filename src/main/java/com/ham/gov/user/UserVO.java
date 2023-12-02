package com.ham.gov.user;

import java.sql.Date;

import com.ham.gov.commons.CommonsVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO extends CommonsVO{

	private String id;
	private String password;
	private String Email;
	private Date Birth;
	private String Name;
	
	
}
