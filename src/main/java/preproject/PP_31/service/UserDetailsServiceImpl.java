package preproject.PP_31.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import preproject.PP_31.model.User;
import preproject.PP_31.repositories.UserRepository;
import preproject.PP_31.security.UserDetailsImpl;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(s);

        if (user.isEmpty() ) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDetailsImpl(user.get() );
    }
}
