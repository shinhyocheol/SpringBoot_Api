package kr.co.platform.api.main.controller;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.platform.api.main.service.ApiMainService;
import kr.co.platform.util.base.BaseController;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = {"/main"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiMainController extends BaseController{
	
	private ApiMainService apiMainService;
	
	@RequestMapping(value = {""}, method = {RequestMethod.GET})
	public ResponseEntity<Map<String, Object>> apiGetMain(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> dataMap = validateParams(request);				
		return ResponseEntity.ok(apiMainService.getMainData(dataMap));
	}
	
}
