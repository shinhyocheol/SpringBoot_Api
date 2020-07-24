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
		Map<String, Object> dataMap = validateParams(request);
		
		resultMap = apiMainService.getMainData(dataMap);

		return JSONUtil.returnJSON(response, resultMap);
		
//		try {
//			if("NO_EXIST_DATA".equals(resultMap.get("msg"))) {
//				return JSONUtil.returnJSON(response, resultMap, 700);
//			} else if("INTNAL_SERVER_ERROR".equals(resultMap.get("msg"))) {
//				return JSONUtil.returnJSON(response, resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultMap.put("result", false);
//			return JSONUtil.returnJSON(response, resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
	
}
