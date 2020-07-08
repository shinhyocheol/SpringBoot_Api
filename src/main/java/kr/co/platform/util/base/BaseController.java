package kr.co.platform.util.base;


import java.nio.ByteBuffer;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.co.platform.util.sql.SqlUtil;


public class BaseController {
	
	protected String getReturnUrl(HttpServletRequest request) {
		return request.getHeader("referer");
	}
	
	protected Map<String, String> validateParam(HttpServletRequest request) {
		Map<String, String[]> reqMap = request.getParameterMap();
		Map<String, String> resultMap = new HashMap<String, String>();
		
		@SuppressWarnings("rawtypes")
		Enumeration reqEnum = request.getParameterNames();
		for(int i = 0; i < reqMap.size(); i++) {
			String key = (String)reqEnum.nextElement();
			String value = ((String[])reqMap.get(key))[0];
			
//			if(!SqlUtil.injectionCheck(value)) {
				resultMap.put(key, value);
//			} else {
//				return null;
//			}
		}
		
		return resultMap;
	}
	
	protected Map<String, String> validateParam(HttpServletRequest request, String fromEncode, String toEncode, String finalEncode) {
		Map<String, String> resultMap = new HashMap<String, String>();
		
		try {
			Map<String, String[]> reqMap = request.getParameterMap();
			
			@SuppressWarnings("rawtypes")
			Enumeration reqEnum = request.getParameterNames();
			for(int i = 0; i < reqMap.size(); i++) {
				String key = (String)reqEnum.nextElement();
				String value = new String(((String[])reqMap.get(key))[0].getBytes(fromEncode), toEncode);
				
				CharBuffer cbuffer = CharBuffer.wrap((new String(value.getBytes(toEncode), toEncode)).toCharArray());
				Charset utf8charset = Charset.forName(finalEncode);
				ByteBuffer bbuffer = utf8charset.encode(cbuffer);

				value = new String(bbuffer.array());
				
				if(!SqlUtil.injectionCheck(value)) {
					resultMap.put(key, value);
				} else {
					return null;
				}
			}
		} catch(Exception e) {
			resultMap = null;
		}
		return resultMap;
	}
	

	protected Map<String, String> validateParam(HttpServletRequest request, String jsonString) {
		Map<String, String> resultMap = new HashMap<String, String>();
		Map<String, String[]> reqMap = request.getParameterMap();
		
		if(reqMap != null) {
			@SuppressWarnings("rawtypes")
			Enumeration reqEnum = request.getParameterNames();
			for(int i = 0; i < reqMap.size(); i++) {
				String key = (String)reqEnum.nextElement();
				String value = ((String[])reqMap.get(key))[0];
				
				if(!SqlUtil.injectionCheck(value)) {
					resultMap.put(key, value);
				} else {
					return null;
				}
			}
		}
		if(jsonString != null) {
			try {
				if(jsonString.startsWith("{")) {
					JSONObject obj = new JSONObject(jsonString);
					Iterator iter = obj.keys();
					while(iter.hasNext()) {
						String key = iter.next().toString();
						String value = obj.getString(key);
						resultMap.put(key, value);
					}
				} else {
					String[] arrParams = jsonString.split("&");
					for(String s : arrParams) {
						String[] s1 = s.split("=");
						resultMap.put(s1[0], java.net.URLDecoder.decode(s1[1], "UTF-8"));
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return resultMap;
	}
	protected Map<String, Object> validateParams(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, String[]> reqMap = request.getParameterMap();
		@SuppressWarnings("rawtypes")
		Enumeration reqEnum = request.getParameterNames();
		for(int i = 0; i < reqMap.size(); i++) {
			String key = (String)reqEnum.nextElement();
			
			if(reqMap.get(key).length > 1) {
				String[] value = reqMap.get(key);
				
				for(String s : value) {
					if(SqlUtil.injectionCheck(s)) {
						return null;
					}
				} 
				List<String> valueList = Arrays.asList(value);
				resultMap.put(key, valueList);
			} else {
				String value = ((String[])reqMap.get(key))[0];
				
				if(!SqlUtil.injectionCheck(value)) {
					resultMap.put(key, value);
				} else {
					return null;
				}
			}
		}
		
		return resultMap;
	}
	
	protected Map<String, Object> validateParams(HttpServletRequest request, String jsonString) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, String[]> reqMap = request.getParameterMap();
		
		if(reqMap != null) {
			@SuppressWarnings("rawtypes")
			Enumeration reqEnum = request.getParameterNames();
			for(int i = 0; i < reqMap.size(); i++) {
				String key = (String)reqEnum.nextElement();
				
				if(reqMap.get(key).length > 1) {
					String[] value = reqMap.get(key);
					
					for(String s : value) {
						if(SqlUtil.injectionCheck(s)) {
							return null;
						}
					} 
						
					List<String> valueList = Arrays.asList(value);
					resultMap.put(key, valueList);
				} else {
					String value = ((String[])reqMap.get(key))[0];
					
					if(!SqlUtil.injectionCheck(value)) {
						resultMap.put(key, value);
					} else {
						return null;
					}
				}
			}
		}
		

		if(jsonString != null) {
			try {
				if(jsonString.startsWith("{")) {
					JSONObject obj = new JSONObject(jsonString);
					Iterator iter = obj.keys();
					while(iter.hasNext()) {
						String key = iter.next().toString();
						String value = obj.getString(key);
						
						if(value.startsWith("[")) {
							value = value.replace("[", "").replace("]", "");
							List<String> valueList = Arrays.asList(value.split(","));
							resultMap.put(key, valueList);		
						} else {
							resultMap.put(key, value);
						}
					}
				} else {
					String[] arrParams = jsonString.split("&");
					for(String s : arrParams) {
						String[] s1 = s.split("=");
						resultMap.put(s1[0], java.net.URLDecoder.decode(s1[1], "UTF-8"));
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return resultMap;
	}
}