package kr.co.platform.api.sign.dao;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Mapper;

import kr.co.platform.api.sign.dto.LoginInfo;
import kr.co.platform.api.sign.dto.MemberResultDetail;
import kr.co.platform.api.sign.dto.RegMemberInfo;

@Transactional
@Mapper
public interface ApiSignDAO {

	int insertUserInfo(RegMemberInfo regMember);

	MemberResultDetail selectMemberFindById(LoginInfo info);

	int selectDuplicatedMemberFindById(String memberId);

}
