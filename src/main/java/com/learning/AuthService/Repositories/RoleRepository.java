package com.learning.AuthService.Repositories;

import com.learning.AuthService.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    List<Role> findAllByIdIn(List<UUID> uuids);
}
