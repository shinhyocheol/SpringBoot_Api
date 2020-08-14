package kr.co.platform.api.sign.service;


import kr.co.platform.api.sign.dto.Member;

public interface ApiSignService {

	boolean insertUserInfo(Member params);

	Member loginUserProcessService(Member params) throws Exception;

}
