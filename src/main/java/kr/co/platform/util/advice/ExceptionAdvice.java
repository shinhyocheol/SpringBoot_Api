package kr.co.platform.util.advice;

import java.sql.SQLException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.co.platform.util.advice.ExceptionAdvice;
import kr.co.platform.util.advice.exception.AuthenticationEntryPointException;
import kr.co.platform.util.advice.exception.Code700Exception;
import kr.co.platform.util.advice.exception.ForbiddenException;
import kr.co.platform.util.advice.exception.UserNotFoundException;
import kr.co.platform.util.advice.exception.DuplicatedException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler({
		Exception.class, 
		SQLException.class, 
		UserNotFoundException.class, 
		DataAccessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> defaultException(Exception e) throws Exception {
		e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler({
    	AccessDeniedException.class, 
    	AuthenticationEntryPointException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> accessDeniedException(Exception e) throws Exception {
    	e.printStackTrace();
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
    
    @ExceptionHandler({
    	HttpMessageNotReadableException.class,
		MethodArgumentNotValidException.class,
		MissingServletRequestParameterException.class,
		UnsatisfiedServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> badRequestException(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		Exception e) throws Exception {	
    	e.printStackTrace();    	
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> forbiddenException(ForbiddenException e) throws Exception {
    	e.printStackTrace();
    	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
    
    @ExceptionHandler(Code700Exception.class)
    public ResponseEntity<String> Error700Exception(Code700Exception e) throws Exception {
    	e.printStackTrace();
    	return ResponseEntity.status(700).body(e.getMessage());
    }
    
    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<String> DuplicatedException(DuplicatedException e) throws Exception {
    	e.printStackTrace();
    	return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
  
}


