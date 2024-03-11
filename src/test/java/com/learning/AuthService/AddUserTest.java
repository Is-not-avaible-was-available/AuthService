package com.learning.AuthService;

import com.learning.AuthService.Models.User;
import com.learning.AuthService.Repositories.RoleRepository;
import com.learning.AuthService.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class AddUserTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Commit
    public void testAddingUserDetails(){

        User user = new User();
        user.setEmail("rajat@gmail.com");
        user.setName("Rajat");
        user.setPassword(bCryptPasswordEncoder.encode("abc123"));
//        user.setRole(roleRepository.findById(2L).get());
        userRepository.save(user);

    }
}
