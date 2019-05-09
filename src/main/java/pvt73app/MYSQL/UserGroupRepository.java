package pvt73app.MYSQL;

import org.springframework.data.repository.CrudRepository;

import pvt73app.MYSQL.Usergroup;

public interface UserGroupRepository extends CrudRepository<Usergroup, Integer> {

    //Iterable<UserGroup> findAll();
}
