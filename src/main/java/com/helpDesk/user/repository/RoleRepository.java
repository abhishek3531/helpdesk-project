package com.helpDesk.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpDesk.user.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{


}
