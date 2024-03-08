package com.learning.AuthService.Service;

import com.learning.AuthService.DTOs.CreateRoleRequestDTO;
import com.learning.AuthService.Models.Role;
import com.learning.AuthService.Repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public String createRole(String name) {
        Role role = new Role();
        role.setName(name);
        roleRepository.save(role);
        return "Success!";
    }
}
