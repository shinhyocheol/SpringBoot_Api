package kr.co.platform.api.sign.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.platform.api.sign.service.ApiSignService;
import kr.co.platform.util.JSON.JSONUtil;
import kr.co.platform.util.base.BaseController;
import kr.co.platform.util.common.IsEmpty;

@RestController
@RequestMapping("")
@CrossOrigin("*")
public class ApiSignController extends BaseController {

	@Autowired
	private ApiSignService apiSignService;
	
	@RequestMapping(value = {"/signup"}, method = {RequestMethod.POST}, 
			params = {"id", "password"})
	public ResponseEntity<String> apiUserJoin (
			ModelMap model,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> dataMap = validateParams(request);
			if(dataMap == null || dataMap.isEmpty()) {
				resultMap.put("result", false);
				return JSONUtil.returnJSON(response, resultMap, HttpStatus.BAD_REQUEST);
			}
			resultMap = apiSignService.insertUserInfo(dataMap);
			
		} catch (Exception e) {
			resultMap.put("result", false);
			return JSONUtil.returnJSON(response, resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return JSONUtil.returnJSON(response, resultMap);
	}
	
	@RequestMapping(value = {"/signin"}, method = {RequestMethod.POST},
			params = {"id", "password"})
	public ResponseEntity<String> apiUserSignin(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Map<String, Object> dataMap = validateParams(request);
			if(IsEmpty.check(dataMap)) {
				resultMap.put("result", false);
				return JSONUtil.returnJSON(response, resultMap);
			}
			resultMap = apiSignService.loginUserProcessService(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			return JSONUtil.returnJSON(response, resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return JSONUtil.returnJSON(response, resultMap);
	}
	
}
