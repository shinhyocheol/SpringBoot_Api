package kr.co.platform.api.sign.service;


import kr.co.platform.api.sign.dto.LoginInfo;
import kr.co.platform.api.sign.dto.MemberResultDetail;
import kr.co.platform.api.sign.dto.RegMemberInfo;

public interface ApiSignService {

	boolean insertUserInfo(RegMemberInfo regMember) throws Exception;

	MemberResultDetail loginUserProcessService(LoginInfo info) throws Exception;

}
