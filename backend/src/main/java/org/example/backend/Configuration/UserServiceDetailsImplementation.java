package org.example.backend.Configuration;

import lombok.AllArgsConstructor;
import org.example.backend.Entities.SuperUser;
import org.example.backend.Repository.SuperUserRepository;
import org.example.backend.Repository.UserRepository;
import org.example.backend.Service.SuperUserService;
import org.example.backend.Service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceDetailsImplementation implements UserDetailsService {

    private final UserRepository userRepository;
    private final SuperUserRepository superUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return superUserRepository.findSuperUserByEmail(username)
                .map(CustomUserDetails::new)
                .orElseGet(() -> userRepository.findUserByEmail(username)
                        .map(CustomUserDetails::new)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username)));
    }
}
