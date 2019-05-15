/**
 * 
 */
package pvt73app.MYSQL;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface UserTrailsRepository extends CrudRepository<UserTrails, Integer> {
	List<UserTrails> findByUid(Integer uid);
	List<UserTrails> findByFavourite(boolean favourite);

}
