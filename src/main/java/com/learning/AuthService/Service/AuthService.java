package com.learning.AuthService.Service;

import com.learning.AuthService.DTOs.UserDetailsResponseDTO;
import com.learning.AuthService.Exceptions.AlreadyExistsException;
import com.learning.AuthService.Exceptions.InvalidCredentialsException;
import com.learning.AuthService.Exceptions.NotFoundException;
import com.learning.AuthService.Mappers.UserMapper;
import com.learning.AuthService.Models.Role;
import com.learning.AuthService.Models.Session;
import com.learning.AuthService.Models.SessionStatus;
import com.learning.AuthService.Models.User;
import com.learning.AuthService.Repositories.RoleRepository;
import com.learning.AuthService.Repositories.SessionRepository;
import com.learning.AuthService.Repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SessionRepository sessionRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository,
                       SessionRepository sessionRepository, UserMapper userMapper,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.sessionRepository = sessionRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String createNewUser(String email, String name, String password, String roleId) throws NotFoundException, AlreadyExistsException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isPresent()){
            throw new AlreadyExistsException("User with "+email+" already exists!");
        }else{
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            Optional<Role> roleOptional = roleRepository.findById(UUID.fromString(roleId));
            if(roleOptional.isEmpty()){
                throw new NotFoundException("Role not found!");
            }
            Role role = roleOptional.get();
            user.setRole(role);
            userRepository.save(user);
        }
        return "Successfully Signed Up!";
    }

    public ResponseEntity<UserDetailsResponseDTO> login(String userName, String password) throws NotFoundException, InvalidCredentialsException {
        Optional<User> userOptional = userRepository.findUserByEmail(userName);
        if(userOptional.isEmpty()){
            throw new NotFoundException("User Not Found!");
        }
        User user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new InvalidCredentialsException("Invalid Credentials!");
        }
        String token = RandomStringUtils.randomAlphanumeric(30);
        Session session = new Session();
        session.setToken(token);
        session.setUser(user);
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setSessionExpiresAt(LocalDateTime.now().plusHours(24));
        sessionRepository.save(session);
        UserDetailsResponseDTO userDetailsResponseDTO = userMapper.convertUserToUserDetailsDTO(user);
        MultiValueMap<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE,"auth-token:" + token);
        return new ResponseEntity<UserDetailsResponseDTO>(userDetailsResponseDTO, headers, HttpStatus.OK);
    }

    public SessionStatus validateSession(String token, String userId) {
        Optional<Session> sessionOptional = sessionRepository.findSessionByTokenAndUser_Id(token,
                UUID.fromString(userId));
        if(sessionOptional.isEmpty()){
            return SessionStatus.ENDED;
        }
        Session session = sessionOptional.get();
        if(session.getSessionStatus().equals(SessionStatus.ENDED)){
            return SessionStatus.ENDED;
        }
        return SessionStatus.ACTIVE;
    }

    public String logout(String token, String id){
        Optional<Session> sessionOptional = sessionRepository.findSessionByTokenAndUser_Id(token,
                UUID.fromString(id));
        if(sessionOptional.isEmpty()){
            return "";
        }
        Session session = sessionOptional.get();

        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
        return "Logged Out SuccessFully";
    }
}
