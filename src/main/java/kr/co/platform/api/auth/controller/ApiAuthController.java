package kr.co.platform.api.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.platform.api.auth.service.ApiAuthService;
import kr.co.platform.util.JSON.JSONUtil;
import kr.co.platform.util.base.BaseController;

@RestController
@RequestMapping(value = {"/auth"})
@CrossOrigin("*")
public class ApiAuthController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(ApiAuthController.class);
	
	@Autowired
	private ApiAuthService apiAuthService;
	
	@RequestMapping(value = {""}, method = {RequestMethod.GET})
	public ResponseEntity<String> apiGetAuth(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			return JSONUtil.returnJSON(response, resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return JSONUtil.returnJSON(response, resultMap);
	}
}
