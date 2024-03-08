package com.learning.AuthService.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogOutRequestDTO {
    private String token;
    private String userId;
}
