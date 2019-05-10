package pvt73app.MYSQL;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TrailReviewRepository extends CrudRepository<TrailReview, Integer> {
	Optional<TrailReview> findByTid(Integer tid);

}
