package org.devk.blogger.exceptions;

import org.devk.blogger.exceptions.handlers.GlobalExceptionResponse;
import org.springframework.http.ResponseEntity;
/**
 * 
 * @author devinder kashyap
 *
 */
public abstract class BloggerException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6359503123304057854L;

	public abstract  ResponseEntity<GlobalExceptionResponse> getErrorResponse();
	
}
