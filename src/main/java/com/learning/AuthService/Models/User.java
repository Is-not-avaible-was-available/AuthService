package com.learning.AuthService.Models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Entity
@JsonDeserialize(as = User.class)
public class User extends BaseModel{
    @NonNull
   private String name;
   @Column(unique = true)
   @NonNull
   private String email;
   @NonNull
   private String password;
   @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
   @JoinColumn(name = "roleId")
   private Role role;
}
