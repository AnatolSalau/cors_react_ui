package com.example.cors_react_ui.controllers;

import com.example.cors_react_ui.entities.User;
import com.example.cors_react_ui.repositories.UserJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;

@RestController
/*@CrossOrigin(origins = "http://localhost:3000/")*/
@RequestMapping(path = "/api/v1/users")
public class UserController {

      @Autowired
      UserJpaRepository userJpaRepository;

      @GetMapping()
      @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
      public ResponseEntity<List<User>> getUser(CsrfToken csrfToken) {
            String headerName = csrfToken.getHeaderName();
            String token = csrfToken.getToken();
            List<User> all = userJpaRepository.findAll();
            return ResponseEntity
                  .status(HttpServletResponse.SC_OK)
                  .body(all);
      }

      @PostMapping()
      @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
      public ResponseEntity<List<User>> postUser() {
            List<User> all = userJpaRepository.findAll();
            return ResponseEntity
                  .status(HttpServletResponse.SC_OK)
                  .body(all);
      }
}
