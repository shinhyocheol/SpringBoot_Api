package kr.co.platform.api.sign.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.platform.api.sign.dao.ApiSignDAO;
import kr.co.platform.api.sign.service.ApiSignService;

@Service("apiSignService")
public class ApiSignServieImpl implements ApiSignService {

    @Autowired
    private ApiSignDAO apiSignDao;

    @Override
    public Map<String, Object> insertUserInfo(Map<String, Object> dataMap) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            int result = 0;
            result = apiSignDao.insertUserInfo(dataMap);
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
