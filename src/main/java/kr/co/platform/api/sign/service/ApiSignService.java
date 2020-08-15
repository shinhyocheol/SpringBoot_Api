package kr.co.platform.api.sign.service;


import kr.co.platform.api.sign.dto.Member;

public interface ApiSignService {

	boolean insertUserInfo(Member login) throws Exception;

	Member loginUserProcessService(Member info) throws Exception;

}
