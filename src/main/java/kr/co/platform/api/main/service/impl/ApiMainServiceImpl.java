package kr.co.platform.api.main.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.platform.api.main.dao.ApiMainDAO;
import kr.co.platform.api.main.service.ApiMainService;

@Service("apiMainService")
public class ApiMainServiceImpl implements ApiMainService{
	
	@Autowired
	private ApiMainDAO apiMainDao;

	@Override
	public Map<String, Object> getMainData(Map<String, Object> dataMap) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("result", true);
			resultMap.put("msg", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", "INTERNAL_SERVER_ERROR");
		}
		return resultMap;
	}
	
}
