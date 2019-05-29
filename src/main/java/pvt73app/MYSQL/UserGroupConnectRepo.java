package pvt73app.MYSQL;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pvt73app.MYSQL.UserGroupConnect;

public interface UserGroupConnectRepo extends CrudRepository<UserGroupConnect, Integer> {

	List<UserGroupConnect> findByUid(int uid);
	List<UserGroupConnect> findByGid(int gid);

}
