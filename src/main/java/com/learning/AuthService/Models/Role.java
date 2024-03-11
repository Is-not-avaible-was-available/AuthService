package com.learning.AuthService.Models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="roles")
@JsonDeserialize(as = Role.class)
public class Role extends BaseModel{
    private String name;
}
