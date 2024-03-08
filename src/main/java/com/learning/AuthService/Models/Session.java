package com.learning.AuthService.Models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
public class Session extends BaseModel{
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;
    private String token;
    private LocalDateTime sessionExpiresAt;
    @ManyToOne
    private User user;
}
