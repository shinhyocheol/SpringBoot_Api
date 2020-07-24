package kr.co.platform.exception;


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
import org.springframework.web.servlet.NoHandlerFoundException;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerService {
	
	/**
	 * @설명 : 서버에서 해석할 수 없는 요청이 들어온 경우 실행되는 예외처리 핸들러
	 */
	@ExceptionHandler({ 
		HttpMessageNotReadableException.class,
		MethodArgumentNotValidException.class,
		MissingServletRequestParameterException.class,
		UnsatisfiedServletRequestParameterException.class
	})
	public ResponseEntity<Object> BadRequestException(Exception e) {
        e.printStackTrace();
        log.warn("Exception Msg", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
	
	/**
	 * @설명 : 클래스를 찾을 수 없을 때(즉, 없는 파일을 실행하고자 할때) 실행되는 예외처리 핸들러
	 */
	@ExceptionHandler({NotFoundException.class, NoHandlerFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> ClassNotFoundException(Exception e) {
		e.printStackTrace();
		log.warn("Exception Msg", e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	/**
	 * @설명 : 접근권한이 없는 경우 실행되는 예외처리 핸들러
	 */
	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<Object> AccessDeniedException(Exception e) {
		e.printStackTrace();
		log.warn("Exception Msg", e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}
	
	/**
	 * @설명 : 위에 작성된 예외 이외의 예상치 못한 에러를 만났을 때 기본적으로 실행되는 예외처리 핸들러
	 */
	@ExceptionHandler({Exception.class, RuntimeException.class})
	public ResponseEntity<Object> ServerException(Exception e) {
		e.printStackTrace();
		log.warn("Exception Msg", e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
}
