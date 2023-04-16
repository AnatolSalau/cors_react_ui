package com.example.cors_react_ui.services;

import com.example.cors_react_ui.entities.User;
import com.example.cors_react_ui.exeptions.CustomRuntimeException;
import com.example.cors_react_ui.repositories.UserJpaRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCRUDService {
      @Autowired
      private UserJpaRepository userJpaRepository;
      public List<User> findAllUsers() {
            List<User> allUsers = userJpaRepository.findAll() ;
            if(allUsers.isEmpty() || allUsers == null) {
                  throw new CustomRuntimeException(
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "users table is empty"
                  ) ;
            }
            return allUsers;
      }
      public User findUserByLogin(String login) {
            User userByLogin = userJpaRepository.findUserByLogin(login) ;
            if (userByLogin == null) {
                  throw new CustomRuntimeException(
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        ("Cant find user with login : " + login)
                  ) ;
            }
            return userByLogin;
      }
      public User saveUser(User user) {
            System. out.println(user) ;
            User savedUser = userJpaRepository.save(user) ;
            if (savedUser == null) {
                  throw new CustomRuntimeException(
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Saved user is null"
                  ) ;
            }
            return savedUser;
      }
}

