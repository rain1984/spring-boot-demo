package com.springboot.example.springbootdemo;

import com.springboot.example.springbootdemo.configuration.JpaConfiguration;
import com.springboot.example.springbootdemo.department.entity.Department;
import com.springboot.example.springbootdemo.department.repository.DepartmentRepository;
import com.springboot.example.springbootdemo.role.entity.Role;
import com.springboot.example.springbootdemo.role.repository.RoleRepository;
import com.springboot.example.springbootdemo.user.entity.User;
import com.springboot.example.springbootdemo.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class MysqlTest {

    private static Logger logger = LoggerFactory.getLogger(MysqlTest.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RoleRepository roleRepository;

    @Before
    public void initData() {
        userRepository.deleteAll();
        departmentRepository.deleteAll();
        roleRepository.deleteAll();

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



        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.notNull(role.getId());
        User user1 = new User();
        user1.setName("allen");
        user1.setDepartment(department1);
        List<Role> roles1 = roleRepository.findAll();
        Assert.notNull(roles1);
        user1.setRoles(roles1);
        userRepository.save(user1);
        Assert.notNull(user1.getId());
    }
    @Test
    public void findPage() {
        Pageable pageable = new PageRequest(0,10,new Sort(Sort.Direction.ASC,"id"));
        Page<User> page = userRepository.findAll(pageable);
        Assert.notNull(page);
        for (User user: page.getContent()) {
            logger.info("===user=== user name:{}, department name:{},role name:{}",user.getName(),user.getDepartment().getName(),user.getRoles().toString());
        }
    }
}
