package com.learning.AuthService.Repositories;

import com.learning.AuthService.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findSessionByTokenAndUser_Id(String token, Long userId);
}
