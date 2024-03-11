package com.learning.AuthService;

import com.learning.AuthService.Models.Role;
import com.learning.AuthService.Repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class CreateRoleTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Commit
    public void addRoleTest(){
        Role role = new Role();
        role.setName("Admin");

        roleRepository.save(role);
    }
}
