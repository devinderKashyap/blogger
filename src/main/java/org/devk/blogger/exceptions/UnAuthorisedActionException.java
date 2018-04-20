package org.devk.blogger.exceptions;

import org.devk.blogger.exceptions.handlers.GlobalExceptionResponse;
import org.springframework.http.ResponseEntity;
/**
 * 
 * @author devinder kashyap
 *
 */
public class UnAuthorisedActionException extends BloggerException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7722012282852295971L;
	String cause;
	
	public UnAuthorisedActionException(String cause) {
		this.cause=cause;
	}

	@Override
	public ResponseEntity<GlobalExceptionResponse> getErrorResponse() {
		ResponseEntity<GlobalExceptionResponse> entity = ResponseEntity.status(401).body(new GlobalExceptionResponse("401",cause));
		return entity;
	}
	
}
