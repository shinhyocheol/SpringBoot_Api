package kr.co.platform.api.user.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.platform.api.user.dao.ApiUserDAO;
import kr.co.platform.util.base.BaseDAOImpl;

@Transactional
@Repository("apiUserDao")
public class ApiUserDAOImpl extends BaseDAOImpl implements ApiUserDAO {

    @Override
    public int insertUserInfo(Map<String, Object> dataMap) {
        return 0;
    }

}
