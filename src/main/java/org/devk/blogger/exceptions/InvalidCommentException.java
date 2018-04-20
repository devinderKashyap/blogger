package org.devk.blogger.exceptions;

import org.devk.blogger.exceptions.handlers.GlobalExceptionResponse;
import org.springframework.http.ResponseEntity;
/**
 * 
 * @author devinder kashyap
 *
 */
public class InvalidCommentException extends BloggerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5373585809670331608L;
	String cmtid;
	public InvalidCommentException(String cmt) {
		this.cmtid=cmt;
	}
	@Override
	public ResponseEntity<GlobalExceptionResponse> getErrorResponse() {
		ResponseEntity<GlobalExceptionResponse> entity = ResponseEntity.status(400).body(new GlobalExceptionResponse("400","invalid comment id : "+cmtid));
		return entity;
	}

}
