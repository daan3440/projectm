package pvt73app.MYSQL;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@ComponentScan
public interface UserRepository extends CrudRepository<User, Integer> {
    
//    List<User> findByName(String fname, String lname);
    Optional<User> findById(Integer id);
   
    
}