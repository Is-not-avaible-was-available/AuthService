package com.learning.AuthService.Security;

import com.learning.AuthService.Models.User;
import com.learning.AuthService.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByEmail(username);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("Not Found!");
        }
        User user = userOptional.get();
        return new CustomUserDetails(user);
    }
}
