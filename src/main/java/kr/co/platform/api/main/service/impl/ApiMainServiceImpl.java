package kr.co.platform.api.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.platform.api.main.dao.ApiMainDAO;
import kr.co.platform.api.main.service.ApiMainService;

@Service("apiMainService")
public class ApiMainServiceImpl implements ApiMainService{
	
	@Autowired
	private ApiMainDAO apiMainDao;
	
}
