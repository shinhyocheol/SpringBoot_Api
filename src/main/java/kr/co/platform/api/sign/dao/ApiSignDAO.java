package kr.co.platform.api.sign.dao;

import java.util.Map;

public interface ApiSignDAO {

	int insertUserInfo(Map<String, Object> dataMap);

	Map<String, Object> selectUserInfoById(Map<String, Object> dataMap);

}
