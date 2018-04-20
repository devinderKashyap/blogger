package org.devk.blogger.users;

import java.util.List;

import org.devk.blogger.blogs.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * @author devinder kashyap
 *
 */
@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	UsersDAO dao;

	public void setDao(UsersDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public User registerUser(@RequestBody User user) {
		return dao.registerUser(user);
	}
	
/*	
 * username based apis
 * 
 * @RequestMapping(value="/{userName}")
	public User getUser(@PathVariable String userName) {
		return dao.getUser(userName);
	}

	@RequestMapping(value="/{userName}",method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable String userName) {
		System.out.println("UsersController.deleteUser()"+userName);
		dao.deleteUser(userName);
	}
	@RequestMapping(value="/{userName}",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.PUT)
	public User updateUser(@PathVariable String userName,@RequestBody User user) {
		return dao.updateUser(userName,user);
	}
*/

	@RequestMapping(value="/{id}")
	public User getUserById(@PathVariable String id) {
		return dao.getUserById(id);
	}

	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void deleteUserById(@PathVariable String id) {
		System.out.println("UsersController.deleteUser()"+id);
		dao.deleteUserById(id);
	}
	
	@RequestMapping(value="/{id}",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.PUT)
	public User updateUserById(@PathVariable String id,@RequestBody User user) {
		return dao.updateUserById(id,user);
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<User> getAllUsers(){
		return dao.getAllUser();
	}
	
	@RequestMapping(value="/{id}/blogs",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Blog> getAllBlogsByUserId(@PathVariable String id){
		return dao.getAllBlogsByUserId(id);
	}
}
