package org.devk.blogger.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.devk.blogger.blogs.Blog;
import org.devk.blogger.blogs.BlogsRepo;
import org.devk.blogger.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @author devinder kashyap
 *
 */
@Service
public class UsersDAO {
	@Autowired
	UserRepo repo;
	
	@Autowired
	BlogsRepo blogRepo;
	
	public User registerUser(User user) {
		return repo.save(user);
	}

	public User getUser(String userName) {
		return repo.findByUsername(userName);
	}
	
	public void deleteUser(String userName) {
		User temp = repo.findByUsername(userName);
		if(temp!=null) {
			repo.deleteByUsername(userName);
		}
		throw new UserNotFoundException(userName);
	}

	public User updateUser(String userName, User user) {
		User temp = repo.findByUsername(userName);
		if(temp!=null) {
			user.setName(userName);
			return repo.save(user);
		}
		throw new UserNotFoundException(userName);
	}
	
	public User getUserById(String id) {
		Optional<User> temp = repo.findById(id);
		if(temp.isPresent()) {
			return temp.get();
		}
		throw new UserNotFoundException(id);
	}
	
	public void deleteUserById(String id) {
		Optional<User> temp = repo.findById(id);
		if(temp.isPresent()) {
			repo.deleteById(id);
			return;
		}
		throw new UserNotFoundException(id);
	}

	public User updateUserById(String id, User user) {
		Optional<User> temp = repo.findById(id);
		if(temp.isPresent()) {
			user.setId(id);
			return repo.save(user);
		}
		throw new UserNotFoundException(id);
	}

	public List<User> getAllUser() {
		List<User> users = new ArrayList<>();
		repo.findAll().forEach(users::add);
		return users;
	}

	public List<Blog> getAllBlogsByUserId(String userId) {
		Optional<User> temp = repo.findById(userId);
		if(temp.isPresent()) {
			return blogRepo.findAllByUserid(userId);
		}
		throw new UserNotFoundException(userId);
	}
}
