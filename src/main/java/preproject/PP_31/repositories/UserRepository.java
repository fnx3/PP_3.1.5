package preproject.PP_31.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import preproject.PP_31.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
