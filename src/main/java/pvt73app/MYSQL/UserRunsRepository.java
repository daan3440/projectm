package pvt73app.MYSQL;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRunsRepository extends CrudRepository<UserRuns, Integer> {

	Iterable<UserRuns> findByTid(int tid);

	
}
