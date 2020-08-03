package kr.co.platform.util.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.co.platform.util.advice.ExceptionAdvice;
import kr.co.platform.util.advice.exception.AuthenticationEntryPointException;
import kr.co.platform.util.advice.exception.Code700Exception;
import kr.co.platform.util.advice.exception.ForbiddenException;
import kr.co.platform.util.advice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
	
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<String> defaultException(Exception e) throws Exception {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<String> userNotFound(UserNotFoundException e) throws Exception {
    	e.printStackTrace();
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(AuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> authenticationEntryPointException(AuthenticationEntryPointException e) 
    		throws Exception {
    	e.printStackTrace();
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> accessDeniedException(AccessDeniedException e) throws Exception {
    	e.printStackTrace();
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
    
    @ExceptionHandler({HttpMessageNotReadableException.class,
		MethodArgumentNotValidException.class,
		MissingServletRequestParameterException.class,
		UnsatisfiedServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> badRequestException(Exception e) throws Exception {	
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
    
}
