package org.devk.blogger.repos;

import java.util.List;

import org.devk.blogger.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * 
 * @author devinder kashyap
 *
 */
public interface CommentsRepo extends MongoRepository<Comment, String>{

	List<Comment> findAllByBlogid(String blogId);

}
