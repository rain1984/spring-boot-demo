package com.springboot.example.springbootdemo.role.repository;

import com.springboot.example.springbootdemo.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
