package org.devk.blogger.exceptions;

import org.devk.blogger.exceptions.handlers.GlobalExceptionResponse;
import org.springframework.http.ResponseEntity;
/**
 * 
 * @author devinder kashyap
 *
 */
public class UserNotFoundException extends BloggerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -760438504368335000L;
	String userid ;
	
	public UserNotFoundException(String userid) {
		this.userid=userid;
	}
	@Override
	public ResponseEntity<GlobalExceptionResponse> getErrorResponse() {
		ResponseEntity<GlobalExceptionResponse> entity = ResponseEntity.status(400).body(new GlobalExceptionResponse("400","invalid user id : "+userid));
		return entity;
	}

}
