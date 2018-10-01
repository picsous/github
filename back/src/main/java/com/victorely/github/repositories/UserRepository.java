package com.victorely.github.repositories;

import com.victorely.github.entities.Role;
import com.victorely.github.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    User findOne(Long id);

    User findByToken(String token);

    @Query("select u from User u where u.username = :username and u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User findByUsername(String username);

    List<User> findByIdIn(List<Long> ids);

    List<User> findByRolesContains(Role role);
}