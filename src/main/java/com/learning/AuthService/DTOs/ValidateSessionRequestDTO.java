package com.learning.AuthService.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateSessionRequestDTO {
    private String token;
    private String userId;
}
