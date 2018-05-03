package org.devk.blogger.repos;

import java.util.List;
import java.util.Optional;

import org.devk.blogger.exceptions.InvalidBlogException;
import org.devk.blogger.exceptions.UnAuthorisedActionException;
import org.devk.blogger.exceptions.UserNotFoundException;
import org.devk.blogger.models.Blog;
import org.devk.blogger.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @author devinder kashyap
 *
 */
@Service
public class BlogDAO {
	@Autowired
	BlogsRepo blogRepo;
	@Autowired
	UserRepo usersRepo;
	
	
	
	public Blog createBlog(String userId, Blog blog) {
		//check authorization
		//
		Optional<User> temp = usersRepo.findById(userId);
		if(temp.isPresent()) {
			User user = temp.get();
			blog.setUserid(user.getId());
			return blogRepo.save(blog);
		}
		throw new UserNotFoundException(userId);
	}



	public Blog updateBlog(String id, String userId, Blog blog) {
		Optional<Blog> temp = blogRepo.findById(id);
		if(temp.isPresent()) {
			Blog blogOld = temp.get();
			//
			if(blogOld.getUserid().equals(userId) &&
					blogOld.getUserid().equals(blog.getUserid())) {
				return blogRepo.save(blog);
			}
			//throw exception
			new UnAuthorisedActionException("user id mismatch");
		}	
		throw new InvalidBlogException(id);
	}



	public List<Blog> getAllBlogs() {
		return blogRepo.findAll();
	}



/*	public List<Blog> getAllBlogsByUserId(String userId) {
		Optional<User> temp = usersRepo.findById(userId);
		if(temp.isPresent()) {
			return blogRepo.findAllByUserid();
		}
		throw new UserNotFoundException(userId);
	}*/



	public Blog getBlogById(String id) {
		Optional<Blog> temp = blogRepo.findById(id);
		if(temp.isPresent()) {
			return temp.get();
		}
		throw new InvalidBlogException(id);
	}



	public void deleteBlogById(String userId, String id) {
		Optional<User> temp = usersRepo.findById(userId);
		if(temp.isPresent()) {
			Optional<Blog> blg = blogRepo.findById(id);
			if(blg.isPresent()) {
				if(temp.get().getId().equals(blg.get().getUserid())) {
					blogRepo.deleteById(id);
					return;
				}
				throw new UnAuthorisedActionException("user not authorised to delete the blog");
			}
			throw new InvalidBlogException(id);
		}
		throw new UserNotFoundException(userId);
	}

	
}
