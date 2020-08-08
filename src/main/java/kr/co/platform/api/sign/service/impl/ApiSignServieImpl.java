package kr.co.platform.api.sign.service.impl;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.platform.util.advice.exception.ForbiddenException;
import kr.co.platform.util.auth.JwtTokenProvider;
import kr.co.platform.api.sign.dao.ApiSignDAO;
import kr.co.platform.api.sign.service.ApiSignService;
import kr.co.platform.util.common.IsEmpty;

@Service("apiSignService")
public class ApiSignServieImpl implements ApiSignService {

    @Autowired
    private ApiSignDAO apiSignDao;
    
    @Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

    @Override
    public Map<String, Object> insertUserInfo(Map<String, Object> dataMap) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
        	// 패스워드 암호화
        	dataMap.put("enc_password", passwordEncoder.encode(dataMap.get("password").toString()));
        	
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

	@Override
	public Map<String, Object> loginUserProcessService(Map<String, Object> dataMap) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Map<String, Object> resultData = apiSignDao.selectUserInfoById(dataMap);
			if(IsEmpty.check(resultData)) {
				resultMap.put("result", false);
				resultMap.put("msg", "NO_EXIST_DATA");
				return resultMap;
			}
			if(!passwordEncoder.matches(dataMap.get("password").toString(), 
					resultData.get("member_password").toString())) {
				resultMap.put("result", false);
				resultMap.put("msg", "PASSWORD_DO_NOT_MATCH");
				return resultMap;
			}
			
			String token = jwtTokenProvider.createToken(resultData);
			resultData.remove("member_password");
			resultData.put("x-access-token", token);

			resultMap.put("data", resultData);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", "INTERNAL_SERVER_ERROR");
		}
		return resultMap;
	}

}
