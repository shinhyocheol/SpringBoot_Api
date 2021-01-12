package kr.co.platform.api.sign.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.platform.util.auth.JwtTokenProvider;
import kr.co.platform.api.sign.dto.LoginInfo;
import kr.co.platform.api.sign.dto.MemberResultDetail;
import kr.co.platform.api.sign.dto.RegMemberInfo;
import kr.co.platform.api.sign.service.ApiSignService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiSignController {

	private ApiSignService apiSignService;
	
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping(value = "/signin")
	public ResponseEntity<MemberResultDetail> apiUserSignin(
			@RequestBody LoginInfo login) throws Exception {

		MemberResultDetail result = apiSignService.loginUserProcessService(login);
		
		return ResponseEntity.ok()
							 .header("x-accss-token", jwtTokenProvider.createToken(result))
							 .body(result);
	}

	@PostMapping(value = { "/signup" })
	public ResponseEntity<Boolean> apiUserSignUp(
			@RequestBody @Valid RegMemberInfo info) throws Exception {
		
		return ResponseEntity.ok()
							 .body(apiSignService.insertUserInfo(info));
	}
}



