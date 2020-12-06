package dbx.zzzz.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dbx.zzzz.model.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {

	@Query ("SELECT u from User u WHERE u.username =:username")
	public User getUserByUsername (@Param("username") String username);
	
	//@Query ("SELECT u from User u WHERE u.id =:id")
	//public User getUserById (@Param("id") Long id);	

}
