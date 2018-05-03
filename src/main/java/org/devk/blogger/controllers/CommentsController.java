package org.devk.blogger.controllers;

import java.util.List;

import org.devk.blogger.models.Comment;
import org.devk.blogger.repos.CommentsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * @author devinder kashyap
 *
 */
@RestController
@RequestMapping(value="/blogs/{blogId}/comments")
public class CommentsController {
	
	@Autowired
	CommentsDAO dao;
	
	public void setDao(CommentsDAO dao) {
		this.dao = dao;
	}

	@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Comment postComment(@PathVariable String blogId,@RequestParam String userId,@RequestBody Comment comment) {
		return dao.postComment(blogId,userId,comment);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Comment editComment(@PathVariable String id,@PathVariable String blogId,@RequestParam String userId,@RequestBody Comment comment) {
		return dao.editComment(id,blogId,userId,comment);
	}
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Comment> getCommentsByBlogId(@PathVariable String blogId) {
		return dao.getCommentsByBlogId(blogId);
	}
	/**
	 * not  a gud api
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Comment getCommentById(@PathVariable String id) {
		return dao.getCommentById(id);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void deleteComment(@PathVariable String id,@PathVariable String blogId,@RequestParam String userId) {
		dao.deleteComment(id,blogId,userId);
	}
	
}
