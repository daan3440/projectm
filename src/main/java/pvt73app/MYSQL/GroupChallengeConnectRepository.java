package pvt73app.MYSQL;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface GroupChallengeConnectRepository extends CrudRepository<GroupChallengeConnect, Integer> {
	Optional<GroupChallengeConnect> findByCid(Integer cid);
}
