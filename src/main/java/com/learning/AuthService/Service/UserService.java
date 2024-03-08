package com.learning.AuthService.Service;

import com.learning.AuthService.DTOs.SetRolesRequestDTO;
import com.learning.AuthService.DTOs.UserDetailsResponseDTO;
import com.learning.AuthService.Exceptions.NotFoundException;
import com.learning.AuthService.Mappers.UserMapper;
import com.learning.AuthService.Models.Role;
import com.learning.AuthService.Models.User;
import com.learning.AuthService.Repositories.RoleRepository;
import com.learning.AuthService.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       UserMapper userMapper){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }
    public UserDetailsResponseDTO getUserDetails(String id) throws NotFoundException {
       Optional<User> optionalUser = userRepository.findById(UUID.fromString(id));
       if(optionalUser.isEmpty()){
           throw new NotFoundException("User not found"+id);
       }
       User user = optionalUser.get();
        return userMapper.convertUserToUserDetailsDTO(user);
    }

    public UserDetailsResponseDTO setUserWithRoles(String id, SetRolesRequestDTO requestDTO) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(UUID.fromString(id));
        if(optionalUser.isEmpty()){
            throw new NotFoundException("User not found!");
        }
        User user = optionalUser.get();
        Optional<Role> roleOptional = roleRepository.findById(UUID.fromString(requestDTO.getRoleId()));
        if(roleOptional.isEmpty()){
            throw new NotFoundException("Role not found!");
        }
        Role role = roleOptional.get();
        user.setRole(role);
        User saved = userRepository.save(user);
        return userMapper
                .convertUserToUserDetailsDTO(saved);
    }

}
