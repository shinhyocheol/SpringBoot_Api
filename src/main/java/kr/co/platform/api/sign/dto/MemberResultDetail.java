package kr.co.platform.api.sign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResultDetail {
	
	private int regNo;
	
	private String memberId;
	
	private String memberPassword;
	
	private String authorityLevel;
	
	private String memberName;
	
	private String memberEmail;
	
	private String memberMobile;
	
	private String regDate;
	
}
