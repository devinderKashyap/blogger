package org.devk.blogger.blogs;

import java.util.List;

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
@RequestMapping("/blogs")
public class BlogController {
	
	@Autowired
	BlogDAO dao;
	
	@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Blog createBlog(@RequestParam String userId,@RequestBody Blog blog) {
		return dao.createBlog(userId,blog) ;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Blog updateBlog(@PathVariable String id,@RequestParam String userId,@RequestBody Blog blog) {
		return dao.updateBlog(id,userId,blog);
	}
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Blog> getAllBlogs(){
		return dao.getAllBlogs();
	}
// moved to users controller	
//	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
//	public List<Blog> getAllBlogsByUserId(@RequestParam String userId){
//		return dao.getAllBlogsByUserId(userId);
//	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Blog getBlogById(@PathVariable String id){
		return dao.getBlogById(id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public void deleteBlogById(@RequestParam String userId,@PathVariable String id){
		dao.deleteBlogById(userId,id);
	}
	
}
