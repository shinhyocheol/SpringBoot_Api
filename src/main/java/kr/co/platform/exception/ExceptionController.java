package kr.co.platform.exception;

import java.net.BindException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.co.platform.util.base.BaseController;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);
	
	/**
	 * @설명 : 서버에서 해석할 수 없는 요청이 들어온 경우 발생시키는 예외처리
	 */
	@ExceptionHandler({
		RuntimeException.class, 
		BindException.class, 
		HttpMessageNotReadableException.class,
		MethodArgumentNotValidException.class,
		TypeMismatchException.class,
		MissingServletRequestParameterException.class,
		UnsatisfiedServletRequestParameterException.class
	})
	public ResponseEntity<Object> BadRequestException(final RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
	
	/**
	 * @설명 : 접근권한이 없는 경우 발생되는 예외처리
	 */
	@ExceptionHandler({ AccessDeniedException.class})
	public ResponseEntity<Object> AccessDeniedException(final AccessDeniedException e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}
	
	/**
	 * @설명 : 기본적으로 지정한 예외클래스가 아닌 경우 500으로 예외처리
	 */
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> ServerException(final Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
}
