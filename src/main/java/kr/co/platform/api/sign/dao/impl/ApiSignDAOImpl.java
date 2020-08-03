package kr.co.platform.api.sign.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.platform.api.sign.dao.ApiSignDAO;
import kr.co.platform.util.base.BaseDAOImpl;

@Transactional
@Repository("apiSignDao")
public class ApiSignDAOImpl extends BaseDAOImpl implements ApiSignDAO {
	
	private Class<ApiSignDAO> c = ApiSignDAO.class;	
	
    @Override
    public int insertUserInfo(Map<String, Object> dataMap) {
        return insertUserInfo(dataMap);
    }

}
