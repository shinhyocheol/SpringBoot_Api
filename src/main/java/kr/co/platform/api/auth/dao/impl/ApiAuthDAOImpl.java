package kr.co.platform.api.auth.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.platform.api.auth.dao.ApiAuthDAO;
import kr.co.platform.util.base.BaseDAOImpl;

@Transactional
@Repository("apiAuthDao")
public class ApiAuthDAOImpl extends BaseDAOImpl implements ApiAuthDAO{
	
	private Class<ApiAuthDAO> c = ApiAuthDAO.class;
	
	
}
