package kr.co.platform.api.sign.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.platform.util.auth.JwtTokenProvider;
import kr.co.platform.api.sign.dto.Member;
import kr.co.platform.api.sign.service.ApiSignService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiSignController {

	private ApiSignService apiSignService;
	
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping(value = "/signin", params = { "id", "password" })
	public Member apiUserSignin(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestBody Member login) throws Exception {

		Member result = apiSignService.loginUserProcessService(login);
		response.setHeader("x-access-token", jwtTokenProvider.createToken(result));
		
		return result;
	}

	@PostMapping(value = { "/signup" }, params = { "id", "password" })
	public boolean apiUserSignUp(@RequestBody Member info) throws Exception {
		
		boolean result = apiSignService.insertUserInfo(info);
		
		return result;
	}
}



