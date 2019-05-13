package pvt73app.MYSQL;

import org.springframework.data.repository.CrudRepository;

import pvt73app.MYSQL.UserGroup;

public interface UserGroupRepository extends CrudRepository<UserGroup, Integer> {

    //Iterable<UserGroup> findAll();
}
