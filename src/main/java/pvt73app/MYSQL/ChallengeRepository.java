package pvt73app.MYSQL;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import java.util.List;

public interface ChallengeRepository extends CrudRepository<Challenge, Integer> {
	
	Optional<Challenge> findById(Integer id);
	List<Challenge> findByTid(Integer tid);
}
