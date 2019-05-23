package pvt73app.MYSQL;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ChallengeConnectorRepository extends CrudRepository<ChallengeConnector, Integer> {
	Optional<ChallengeConnector> findByCid(Integer cid);
	List<ChallengeConnector> findByTid(Integer tid);
}
