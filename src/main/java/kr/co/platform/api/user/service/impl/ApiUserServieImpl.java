package kr.co.platform.api.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.platform.api.user.dao.ApiUserDAO;
import kr.co.platform.api.user.service.ApiUserService;

@Service("apiUserService")
public class ApiUserServieImpl implements ApiUserService {

    @Autowired
    private ApiUserDAO apiUserDao;

    @Override
    public Map<String, Object> insertUserInfo(Map<String, Object> dataMap) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            int result = 0;
            result = apiUserDao.insertUserInfo(dataMap);
            if (result == 0) {
                resultMap.put("result", false);
                resultMap.put("msg", "FAILD_INSERT_DATA");
                return resultMap;
            }
            resultMap.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", false);
            resultMap.put("msg", "INTERNAL_SERVER_ERROR");
        }
        return resultMap;
    }

}
