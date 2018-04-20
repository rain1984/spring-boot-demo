package com.springboot.example.springbootdemo.user.web.controller;

import com.springboot.example.springbootdemo.department.entity.Department;
import com.springboot.example.springbootdemo.department.repository.DepartmentRepository;
import com.springboot.example.springbootdemo.role.entity.Role;
import com.springboot.example.springbootdemo.role.repository.RoleRepository;
import com.springboot.example.springbootdemo.user.entity.User;
import com.springboot.example.springbootdemo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RoleRepository roleRepository;
    @GetMapping("/findByName")
    public User find() {
        Department department = new Department();
        department.setName("开发部");
        departmentRepository.save(department);
        Assert.notNull(department.getId());
        Department department1 = new Department();
        department1.setName("行政部");
        departmentRepository.save(department1);
        Assert.notNull(department1.getId());/*
        Department department2 = new Department();
        department2.setName("运营部");
        departmentRepository.save(department2);
        Assert.notNull(department2.getId());
        Department department3 = new Department();
        department3.setName("销售部");
        departmentRepository.save(department3);
        Assert.notNull(department3.getId());*/

        Role role1 = new Role();
        role1.setName("user");
        roleRepository.save(role1);
        Assert.notNull(role1.getId());


        User user = new User();
        user.setName("rain");
        user.setDepartment(department);
        List<Role> roles = roleRepository.findAll();
        Assert.notNull(roles);
        user.setRoles(roles);
        userRepository.save(user);
        Assert.notNull(user.getId());



        Role role5 = new Role();
        role5.setName("admin");
        roleRepository.save(role5);
        Assert.notNull(role5.getId());
        User user1 = new User();
        user1.setName("allen");
        user1.setDepartment(department1);
        List<Role> roles1 = roleRepository.findAll();
        Assert.notNull(roles1);
        user1.setRoles(roles1);
        userRepository.save(user1);
        Assert.notNull(user1.getId());
        return userRepository.findByNameLike("rain");
    }
}
