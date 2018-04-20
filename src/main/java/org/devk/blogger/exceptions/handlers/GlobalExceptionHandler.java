package org.devk.blogger.exceptions.handlers;

import org.devk.blogger.exceptions.BloggerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * 
 * @author devinder kashyap
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BloggerException.class)
	public ResponseEntity<GlobalExceptionResponse> handleException(BloggerException e) {
		System.out.println("GlobalExceptionHandler.handleException()"+e);
		return e.getErrorResponse() ;
	}
}
