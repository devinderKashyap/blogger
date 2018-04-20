package org.devk.blogger.comments;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * 
 * @author devinder kashyap
 *
 */
public interface CommentsRepo extends MongoRepository<Comment, String>{

	List<Comment> findAllByBlogid(String blogId);

}
