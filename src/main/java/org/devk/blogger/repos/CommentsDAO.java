package org.devk.blogger.repos;

import java.util.List;
import java.util.Optional;

import org.devk.blogger.exceptions.InvalidBlogException;
import org.devk.blogger.exceptions.InvalidCommentException;
import org.devk.blogger.exceptions.UnAuthorisedActionException;
import org.devk.blogger.exceptions.UserNotFoundException;
import org.devk.blogger.models.Blog;
import org.devk.blogger.models.Comment;
import org.devk.blogger.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @author devinder kashyap
 *
 */
@Service
public class CommentsDAO {
	
	@Autowired
	CommentsRepo repo;
	@Autowired
	UserRepo usersRepo;
	@Autowired
	BlogsRepo blogRepo;

	public void setRepo(CommentsRepo repo) {
		this.repo = repo;
	}

	public void setUsersRepo(UserRepo usersRepo) {
		this.usersRepo = usersRepo;
	}

	public void setBlogRepo(BlogsRepo blogRepo) {
		this.blogRepo = blogRepo;
	}


	public Comment postComment(String blogId, String userId, Comment comment) {
		Optional<Blog> temp = blogRepo.findById(blogId);
		Optional<User> tempuser = usersRepo.findById(userId);
		if(tempuser.isPresent()) {
			if(temp.isPresent()) {
				comment.setBlogid(blogId);
				comment.setUserid(userId);
				return repo.save(comment);
			}
			throw new InvalidBlogException(blogId);
		}
		throw new UserNotFoundException(userId);
	}

	public Comment editComment(String id, String blogId, String userId, Comment comment) {
		Optional<Blog> temp = blogRepo.findById(blogId);
		Optional<User> tempuser = usersRepo.findById(userId);
		if(tempuser.isPresent()) {
			if(temp.isPresent()) {
					Optional<Comment> cmtOld = repo.findById(id);
					if(cmtOld.isPresent()){
					if(userId.equals(cmtOld.get().getUserid())) {
						//overriding ids
						comment.setBlogid(blogId);
						comment.setUserid(userId);
						comment.setId(id);
						return repo.save(comment);
					}
					throw new UnAuthorisedActionException("a user cannot edit other user's comment");
				}
				throw new InvalidCommentException(id);
			}
			throw new InvalidBlogException(blogId);
		}
		throw new UserNotFoundException(userId);
	}

	public Comment getCommentById(String id) {
		Optional<Comment> cmt = repo.findById(id);
		if(cmt.isPresent()) {
			return cmt.get();
		}
		throw new InvalidCommentException(id);
	}

	public List<Comment> getCommentsByBlogId(String blogId) {
		Optional<Blog> blog = blogRepo.findById(blogId);
		if(blog.isPresent()) {
			List<Comment> cmts = repo.findAllByBlogid(blogId);
			return cmts;
		}
		throw new InvalidBlogException(blogId);
	}

	public void deleteComment(String id, String blogId, String userId) {
		Optional<User> user = usersRepo.findById(userId);
		if(user.isPresent()) {
			Optional<Blog> blog = blogRepo.findById(blogId);
			if(blog.isPresent()) {
				Optional<Comment> cmt = repo.findById(id);
				if(cmt.isPresent()) {
					if(userId.equals(blog.get().getUserid()) || userId.equals(cmt.get().getUserid())) {
						repo.deleteById(id);
						return;
					}
					throw new UnAuthorisedActionException("User not authorised to delete comment");
				}
				throw new InvalidCommentException(id);
			}
			throw new InvalidBlogException(blogId);
		}
		throw new UserNotFoundException(userId);
	}
	
}
