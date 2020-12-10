package kr.co.platform.api.sign.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegMemberInfo {

	@NotBlank(message = "'memberId' is a required input value")
	private String memberId;
	
	@NotBlank(message = "'memberPassword' is a required input value")
	private String memberPassword;
	
	@NotBlank(message = "'authorityLevel' is a required input value")
	private String authorityLevel;
	
	@NotBlank(message = "'memberName' is a required input value")
	private String memberName;
	
	@NotBlank(message = "'memberEmail' is a required input value")
	private String memberEmail;
	
	@NotBlank(message = "'memberMobile' is a required input value")
	private String memberMobile;
	
	
}
