package com.example.cors_react_ui.controllers;

import com.example.cors_react_ui.entities.User;
import com.example.cors_react_ui.repositories.UserJpaRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/admins")
public class AdminController {
      @Autowired
      private UserJpaRepository userJpaRepository;

      @GetMapping()
      @PreAuthorize("hasAuthority('ADMIN')")
      public ResponseEntity<List<User>> getUser() {
            List<User> all = userJpaRepository.findAll();
            return ResponseEntity
                  .status(HttpServletResponse.SC_OK)
                  .body(all);
      }

      @PostMapping()
      @PreAuthorize("hasAuthority('ADMIN')")
      public ResponseEntity<String> postAdmin() {
            return ResponseEntity.ok("Post admin");
      }
}
