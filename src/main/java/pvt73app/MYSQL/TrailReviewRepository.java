package pvt73app.MYSQL;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TrailReviewRepository extends CrudRepository<TrailReview, Integer> {
	List<TrailReview> findByTid(Integer tid);
	Optional<TrailReview> findByUid(Integer uid);
}
