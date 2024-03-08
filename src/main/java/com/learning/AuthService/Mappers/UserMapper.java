package com.learning.AuthService.Mappers;

import com.learning.AuthService.DTOs.UserDetailsResponseDTO;
import com.learning.AuthService.Models.Role;
import com.learning.AuthService.Models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDetailsResponseDTO convertUserToUserDetailsDTO(User user){
        UserDetailsResponseDTO userDetailsResponseDTO = new UserDetailsResponseDTO();
        userDetailsResponseDTO.setEmail(user.getEmail());
        userDetailsResponseDTO.setName(user.getName());
        userDetailsResponseDTO.setRole(user.getRole().getName());
        return userDetailsResponseDTO;
    }
}
