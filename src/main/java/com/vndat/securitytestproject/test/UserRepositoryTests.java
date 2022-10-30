package com.vndat.securitytestproject.test;

import com.vndat.securitytestproject.entity.Role;
import com.vndat.securitytestproject.entity.User;
import com.vndat.securitytestproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("luongngocdat");

        User newUser = new User("vndat", password);
        User savedUser = repo.save(newUser);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testAssignRoleToUser(){
        Integer userId = 3;
        Integer roleId = 3;
        User user = repo.findById(userId).get();
        user.addRole(new Role(roleId));

        User updateUser = repo.save(user);

        assertThat(updateUser.getRoles()).hasSize(1);
    }

    @Test
    public void testAssignRoleToUser1(){
        Integer userId = 1;
        User user = repo.findById(userId).get();
        user.addRole(new Role(1));
        user.addRole(new Role(2));

        User updateUser = repo.save(user);
        assertThat(updateUser.getRoles()).hasSize(2);

    }
}
