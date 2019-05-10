package pvt73app.MYSQL;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ChallengeAttributesRepository extends CrudRepository<ChallengeAttributes, Integer> {

	Optional<ChallengeAttributes> findById(Integer id);
}
