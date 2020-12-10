package kr.co.platform.api.sign.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
	
	@NotBlank(message = "'memberId' is a required input value")
	private String memberId;
	
	@NotBlank(message = "'memberPassword' is a required input value")
	private String memberPassword;
		
}
