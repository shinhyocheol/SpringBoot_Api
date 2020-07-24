package kr.co.platform.api.main.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.platform.api.main.dao.ApiMainDAO;
import kr.co.platform.util.base.BaseDAOImpl;

@Transactional
@Repository("apiMainDao")
public class ApiMainDAOImpl extends BaseDAOImpl implements ApiMainDAO{
	
	private Class<ApiMainDAO> c = ApiMainDAO.class;

	@Override
	public Map<String, Object> selectMainData(Map<String, Object> dataMap) {
		return sqlSession.getMapper(c).selectMainData(dataMap);
	}
	
}
