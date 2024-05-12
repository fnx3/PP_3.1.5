package preproject.PP_31.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import preproject.PP_31.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
