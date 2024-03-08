package com.learning.AuthService.Controller;

import com.learning.AuthService.DTOs.*;
import com.learning.AuthService.Exceptions.AlreadyExistsException;
import com.learning.AuthService.Exceptions.InvalidCredentialsException;
import com.learning.AuthService.Exceptions.NotFoundException;
import com.learning.AuthService.Models.SessionStatus;
import com.learning.AuthService.Repositories.UserRepository;
import com.learning.AuthService.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController{
    private final AuthService authService;

    public AuthController(AuthService authService,
                          UserRepository userRepository) {
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signup(@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) throws NotFoundException, AlreadyExistsException {
        String response = authService.createNewUser(userSignUpRequestDTO.getEmail(),
                userSignUpRequestDTO.getName(), userSignUpRequestDTO.getPassword(),
                userSignUpRequestDTO.getRoleId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetailsResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) throws InvalidCredentialsException, NotFoundException {
        return authService.login(requestDTO.getEmail(), requestDTO.getPassword());
    }
    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validate(@RequestBody ValidateSessionRequestDTO requestDTO){
        SessionStatus sessionStatus = authService.validateSession(requestDTO.getToken(), requestDTO.getUserId());
        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogOutRequestDTO logOutRequestDTO){
        String response = authService.logout(logOutRequestDTO.getToken(), logOutRequestDTO.getUserId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
