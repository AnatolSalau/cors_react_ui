package com.example.cors_react_ui.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A controller for the hello resource.
 * / Header Authorization : Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoidXNlciIsImV4cCI6MTY3NTcwNTk3NCwiaWF0IjoxNjc1NjY5OTc0LCJzY29wZSI6ImFwcCJ9.d5Ud_WevmbdIdKqssTeHyxfIpiOmuKtHa3PpYbifEB7fz7_DcHBx65KgcuWxyVx38BBI39cY-mSYa-dKZnRYWLqzG_CK4CWHap4ClDMyBpjP_O7Q0uHqRjkdCuC4cZQE2GZqwJuo5V4mmnREdrSGMXcHPizaRfERj-DZJEaNA1Y1Mcm6cJCHXHk7kzQI0LnBgg0w02ZJa68VJ_O3lJMulKPqrFZkKFjelBuZ0NgEkJLp5T3rBkWJGBsCI3Itvcubo0J5qTtsj1659V8TIKZkVcdFAfkWkXXQXF1G2raY9kXsQ9bkqSjnEHQLugFL7StTelmZHxpr8nb0Kc9TptKLvA
 */
@RestController
public class IndexController {

/*      @CrossOrigin(origins = "http://localhost:3000/")*/
      @GetMapping("/")
      public Map<String, Object> getPrincipalInfo() {

            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication1 = context.getAuthentication();
            String contextName = authentication1.getName();
            Collection<? extends GrantedAuthority> authorities1 = authentication1.getAuthorities();

            Map<String, Object> info = new HashMap<>();

            info.put("Authentication name", authentication1.getName());
            info.put("contextName ", contextName);
            info.put("context authorities", authorities1);
            return info;
      }
}