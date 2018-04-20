package org.devk.blogger.blogs;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * 
 * @author devinder kashyap
 *
 */
public interface BlogsRepo extends MongoRepository<Blog, String>{

	List<Blog> findAllByUserid(String userId);

}
