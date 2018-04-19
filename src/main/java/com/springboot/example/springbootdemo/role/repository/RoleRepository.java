package com.springboot.example.springbootdemo.role.repository;

import com.springboot.example.springbootdemo.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
