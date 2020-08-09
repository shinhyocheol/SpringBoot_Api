package kr.co.platform.api.main.controller;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.platform.api.main.service.ApiMainService;
import kr.co.platform.util.JSON.JSONUtil;
import kr.co.platform.util.base.BaseController;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/main"})
@CrossOrigin("*")
@Slf4j
public class ApiMainController extends BaseController{
	
	@Autowired
	private ApiMainService apiMainService;
	
	@RequestMapping(value = {""}, method = {RequestMethod.GET})
	public ResponseEntity<String> apiGetMain(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		Map<String, Object> dataMap = validateParams(request);
		
		resultMap = apiMainService.getMainData(dataMap);
		
		return JSONUtil.returnJSON(response, resultMap);
	}
	
}
