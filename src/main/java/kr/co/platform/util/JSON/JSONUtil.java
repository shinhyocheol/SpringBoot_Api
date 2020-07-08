package kr.co.platform.util.JSON;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {
	/**
	 * @params
	 * 		HttpServletResponse
	 * 		JSONArray
	 * @result
	 * 		a result of JSON notation
	 */
	public static ResponseEntity<String> returnJSON(HttpServletResponse response, Map<String, Object> resultMap) throws IOException{
		JSONArray result = JSONArray.fromObject(resultMap);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
		return new ResponseEntity<String>(result.get(0).toString(), responseHeaders, HttpStatus.OK);
	}
	
	public static ResponseEntity<String> returnJSON(HttpServletResponse response, String resultStr) throws IOException{
		JSONObject result = JSONObject.fromObject(resultStr);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
		return new ResponseEntity<String>(result.toString(), responseHeaders, HttpStatus.OK);
	}
	
	public static ResponseEntity<String> returnJSON(HttpServletResponse response, Map<String, Object> resultMap, HttpHeaders responseHeaders) throws IOException{
		JSONArray result = JSONArray.fromObject(resultMap);
		
		return new ResponseEntity<String>(result.get(0).toString(), responseHeaders, HttpStatus.OK);
	}
	
	public static ResponseEntity<String> returnJSON(HttpServletResponse response, String resultStr, HttpHeaders responseHeaders) throws IOException{
		JSONObject result = JSONObject.fromObject(resultStr);
		
		return new ResponseEntity<String>(result.toString(), responseHeaders, HttpStatus.OK);
	}
	
	public static ResponseEntity<String> returnJSON(HttpServletResponse response, Map<String, Object> resultMap, HttpStatus status) throws IOException{
		JSONArray result = JSONArray.fromObject(resultMap);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
		
		return new ResponseEntity<String>(result.get(0).toString(), responseHeaders, status);
	}
	
	public static ResponseEntity<String> returnJSON(HttpServletResponse response, Map<String, Object> resultMap, int status) throws IOException{
		JSONArray result = JSONArray.fromObject(resultMap);
		
		return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON_UTF8).body(result.get(0).toString());
	}
	
	public static ResponseEntity<String> returnJSON(HttpServletResponse response, Map<String, Object> resultMap, HttpHeaders responseHeaders, HttpStatus status) throws IOException{
		JSONArray result = JSONArray.fromObject(resultMap);
		
		return new ResponseEntity<String>(result.get(0).toString(), responseHeaders, status);
	}
	
	public static Object parseJSON(Object in){
		int size = JSONArray.fromObject(in).size();
		
		if(size == 0) return JSONArray.fromObject(in);
		else return JSONArray.fromObject(in).get(0);
	}
}