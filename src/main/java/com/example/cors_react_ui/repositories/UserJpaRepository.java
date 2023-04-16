package com.example.cors_react_ui.repositories;

import com.example.cors_react_ui.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
      List<User> findAll() ;
      User findUserByLogin(String login) ;
      User save(User user) ;
}