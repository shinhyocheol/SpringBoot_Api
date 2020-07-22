package kr.co.platform.api.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.platform.api.auth.dao.ApiAuthDAO;
import kr.co.platform.api.auth.service.ApiAuthService;

@Service("apiAuthService")
public class ApiAuthServiceImpl implements ApiAuthService{
	
	@Autowired
	private ApiAuthDAO apiAuthDao;
	
}
