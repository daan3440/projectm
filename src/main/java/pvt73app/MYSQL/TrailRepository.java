package pvt73app.MYSQL;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TrailRepository extends CrudRepository<Trail, Integer> {
	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete
	
	Optional<Trail> findById(Integer id);
			

}

