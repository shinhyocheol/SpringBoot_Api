package kr.co.platform.api.sign.dao;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Mapper;

import kr.co.platform.api.sign.dto.Member;

@Transactional
@Mapper
public interface ApiSignDAO {

	int insertUserInfo(Member login);

	Member selectUserInfoById(Member info);

}
