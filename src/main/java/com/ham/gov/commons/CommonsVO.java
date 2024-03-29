package com.ham.gov.commons;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ham.gov.user.UserVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommonsVO {
     
	private String regId;
	private Date regDate;
	private String regMenu;
	private String modId;
	private Date modDate;
	private String modMenu;
	
	public CommonsVO() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		
		String id="";
		if(authentication != null) {
			id = authentication.getName(); 
		}
		
		
		Date date=new Date();
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String url = request.getHeader("Referer");
		String menu = "";
		if(url != null) {
			int firstIndex = url.indexOf("/");
			int secondIndex = url.indexOf("/", firstIndex + 1);
			int thirdIndex = url.indexOf("/", secondIndex + 1);
			menu = url.substring(thirdIndex);
		}
		
		
		
		this.regId = id;
		this.regDate = date;
		this.regMenu = menu;
		this.modId = id;
		this.modDate = date;
		this.modMenu = menu;
		
	}
	
	
}
