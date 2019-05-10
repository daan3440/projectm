package pvt73app.MYSQL;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface ChallengeRepository extends CrudRepository<Challenge, Integer> {
	
	Optional<Challenge> findById(Integer id);
}
