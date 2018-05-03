package org.devk.blogger.repos;

import org.devk.blogger.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * 
 * @author devinder kashyap
 *
 */
public interface UserRepo extends MongoRepository<User, String>{

	User findByUsername(String userName);

	void deleteByUsername(String userName);

	/*private static Map<String,User> users = new HashMap<String,User>();
	
	public static User getUser(String username) {
		return users.get(username);
	}
	
	public static User addUser(User user) {
		users.put(user.getUsername(), user);
		return user;
	}
	
	public static User deleteUser(User user) {
		users.get(user.getUsername());
		return user;
	}
	
	public static List<User> getAllUsers(){
		return new ArrayList<>(users.values());
	}

	public static User updateUser(String userName, User user) {
		users.put(userName, user);
		return users.get(userName);
	}

	public static void deleteUser(String userName) {
		users.remove(userName);
	}*/
}
