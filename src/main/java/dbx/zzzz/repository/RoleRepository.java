package dbx.zzzz.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dbx.zzzz.model.Role;

@Repository
public interface RoleRepository extends CrudRepository <Role, Long> {
	@Query ("SELECT r from Role r WHERE r.name =:name")
	public Role getRoleByName (@Param("name") String name);
}
