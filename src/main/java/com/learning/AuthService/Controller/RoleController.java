package com.learning.AuthService.Controller;

import com.learning.AuthService.DTOs.CreateRoleRequestDTO;
import com.learning.AuthService.Models.Role;
import com.learning.AuthService.Service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }
    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody CreateRoleRequestDTO createRoleRequestDTO){
        String response = roleService.createRole(createRoleRequestDTO.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
