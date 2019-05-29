package pvt73app.MYSQL;

import java.util.Optional;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserAdminGroupRepository extends CrudRepository<UserAdminGroup, Integer> {
	Optional<UserAdminGroup> findByUid(int uid);
	
}
