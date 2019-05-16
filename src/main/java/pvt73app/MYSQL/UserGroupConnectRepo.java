package pvt73app.MYSQL;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import pvt73app.MYSQL.UserGroupConnect;

public interface UserGroupConnectRepo extends CrudRepository<UserGroupConnect, Integer> {

	Optional<UserGroupConnect> findByUid(int uid);

}
