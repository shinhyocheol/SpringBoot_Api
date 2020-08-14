package kr.co.platform.api.sign.service.impl;

import java.util.HashMap;


import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.platform.util.advice.exception.Code700Exception;
import kr.co.platform.util.advice.exception.ForbiddenException;
import kr.co.platform.api.sign.dto.Member;
import kr.co.platform.api.sign.dao.ApiSignDAO;
import kr.co.platform.api.sign.service.ApiSignService;
import kr.co.platform.util.common.IsEmpty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service("apiSignService")
public class ApiSignServieImpl implements ApiSignService {

    private ApiSignDAO apiSignDao;
    
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Member loginUserProcessService(Member params) throws Exception {
		Member result = apiSignDao.selectUserInfoById(params);
		if (IsEmpty.check(result)) {
			throw new Code700Exception("There is no Result Data");
		}
		if (!passwordEncoder.matches(params.getPassword(), result.getPassword())) {
			throw new ForbiddenException("Passwords do not match");
		}
		return result;
	}
	
    @Override
    public boolean insertUserInfo(Member params) {
    	params.setPassword(passwordEncoder.encode(params.getPassword()));
		int result = apiSignDao.insertUserInfo(params);
		if (IsEmpty.check(result))
			throw new Code700Exception("The query was executed normally, but not a single data was affected");

		return true;
    }

}


