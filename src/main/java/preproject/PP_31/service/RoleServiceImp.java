package preproject.PP_31.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preproject.PP_31.model.Role;
import preproject.PP_31.repositories.RoleRepository;

import java.util.List;

@Service
@Transactional
public class RoleServiceImp implements RoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleServiceImp(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> listRoles() {
        return repository.findAll();
    }

}
