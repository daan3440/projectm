package pvt73app.MYSQL;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ChallengeConnectorRepository extends CrudRepository<ChallengeConnector, Integer> {
	Optional<ChallengeConnector> findByCid(Integer cid);

}
