package pvt73app.MYSQL;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserAdminGroupRepository extends CrudRepository<UserAdminGroup, Integer> {
	Optional<UserAdminGroup> findByUid(int uid);
	
}
