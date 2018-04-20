package org.devk.blogger.exceptions;

import org.devk.blogger.exceptions.handlers.GlobalExceptionResponse;
import org.springframework.http.ResponseEntity;
/**
 * 
 * @author devinder kashyap
 *
 */
public class InvalidBlogException extends BloggerException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5210320566936930016L;
	String blogId;
	
	public InvalidBlogException(String blogId) {
		this.blogId=blogId;
	}

	@Override
	public ResponseEntity<GlobalExceptionResponse> getErrorResponse() {
		ResponseEntity<GlobalExceptionResponse> entity = ResponseEntity.status(400).body(new GlobalExceptionResponse("400","invalid blog id : "+blogId));
		return entity;
	}

}
