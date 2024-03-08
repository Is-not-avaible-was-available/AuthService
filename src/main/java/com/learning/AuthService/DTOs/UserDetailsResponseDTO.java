package com.learning.AuthService.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDetailsResponseDTO {

    private String name;
    private String email;
    private String role;
}
