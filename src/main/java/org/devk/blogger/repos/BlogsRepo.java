package org.devk.blogger.repos;

import java.util.List;

import org.devk.blogger.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * 
 * @author devinder kashyap
 *
 */
public interface BlogsRepo extends MongoRepository<Blog, String>{

	List<Blog> findAllByUserid(String userId);

}
