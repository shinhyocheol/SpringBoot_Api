package kr.co.platform.api.main.controller;

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

import kr.co.platform.api.main.service.ApiMainService;
import kr.co.platform.util.JSON.JSONUtil;
import kr.co.platform.util.base.BaseController;

@RestController
@RequestMapping(value = {"/main"})
@CrossOrigin("*")
public class ApiMainController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(ApiMainController.class);
	
	@Autowired
	private ApiMainService apiMainService;
	
	@RequestMapping(value = {""}, method = {RequestMethod.GET})
	public ResponseEntity<String> apiGetMain(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			
			resultMap.put("result", true);
			resultMap.put("msg", "SUCCESS!!");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			return JSONUtil.returnJSON(response, resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return JSONUtil.returnJSON(response, resultMap);
	}
	
}
