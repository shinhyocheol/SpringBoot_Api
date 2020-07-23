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
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.co.platform.util.base.BaseController;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler({
		RuntimeException.class, 
		BindException.class, 
		HttpMessageNotReadableException.class,
		MethodArgumentNotValidException.class,
		TypeMismatchException.class
	})
	public ResponseEntity<Object> BadRequestException(final RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
	
	@ExceptionHandler({ AccessDeniedException.class})
	public ResponseEntity<Object> AccessDeniedException(final AccessDeniedException e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> ServerException(final Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
}
