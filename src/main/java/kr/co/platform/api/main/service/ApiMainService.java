package kr.co.platform.api.main.service;

import java.util.Map;

public interface ApiMainService {

	Map<String, Object> getMainData(Map<String, Object> dataMap);
	
	Map<String, Object> getMainListData(Map<String, Object> dataMap);

}
