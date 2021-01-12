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
	
	/**
	 * @method 설명: 사용자 로그인
	 * 				1. 아이디와 비밀번호를 매개변수로 넘겨받은 후 유효성을 검증한다.
	 *  			2. 아이디를 기반으로 회원정보를 검색한다.
	 *  			3. 비밀번호를 비교하여 정보의 일치여부를 판단한다.
	 *  			4. 모든 과정이 진행되면 JWT를 통해 사용자 인증토큰을 발급받고, 응답헤더에 실어서 클라이언트에게 응답한다.
	 * @param login
	 * @throws Exception
	 */
	@PostMapping(value = "/signin")
	public ResponseEntity<MemberResultDetail> apiUserSignin(
			@RequestBody LoginInfo login) throws Exception {

		MemberResultDetail result = apiSignService.loginUserProcessService(login);
		
		return ResponseEntity.ok()
							 .header("x-accss-token", jwtTokenProvider.createToken(result))
							 .body(result);
	}

	 
	@PostMapping(value = { "/signup" }, params = { "memberId", "memberPassword" })
	public ResponseEntity<Boolean> apiUserSignUp(
			@RequestBody @Valid RegMemberInfo info) throws Exception {
		
		return ResponseEntity.ok()
							 .body(apiSignService.insertUserInfo(info));
	}
}



