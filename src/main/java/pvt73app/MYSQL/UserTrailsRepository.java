/**
 * 
 */
package pvt73app.MYSQL;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface UserTrailsRepository extends CrudRepository<UserTrails, Integer> {
	Optional<UserTrails> findByUid(Integer uid);

}
