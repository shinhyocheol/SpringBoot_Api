package kr.co.platform.api.sign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	private int reg_no;
	
	private String id;
	
	private String password;
	
	private String authority_level;
	
	private String name;
	
	private String email;
	
	private String mobile;
	
	private String reg_date;
	
	private String mod_date;
	
}
