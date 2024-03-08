package com.learning.AuthService.Controller;

import com.learning.AuthService.DTOs.SetRolesRequestDTO;
import com.learning.AuthService.DTOs.UserDetailsResponseDTO;
import com.learning.AuthService.Exceptions.NotFoundException;
import com.learning.AuthService.Models.User;
import com.learning.AuthService.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsResponseDTO> getUserDetails(@PathVariable("id") String id) throws NotFoundException {
        UserDetailsResponseDTO userDetailsResponseDTO = userService.getUserDetails(id);
        return new ResponseEntity<>(userDetailsResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/roles/{id}")
    public ResponseEntity<UserDetailsResponseDTO> setUserRoles(
            @PathVariable String id, @RequestBody SetRolesRequestDTO requestDTO) throws NotFoundException {
        UserDetailsResponseDTO userDetailsResponseDTO = userService.setUserWithRoles(id, requestDTO);
        return new ResponseEntity<>(userDetailsResponseDTO, HttpStatus.OK);
    }
}
