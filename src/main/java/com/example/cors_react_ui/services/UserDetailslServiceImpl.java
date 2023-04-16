package com.example.cors_react_ui.services;


import com.example.cors_react_ui.entities.User;
import com.example.cors_react_ui.exeptions.CustomRuntimeException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailslServiceImpl implements UserDetailsService {

      @Autowired
      private UserCRUDService userCRUDService;

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User userByLogin = userCRUDService.findUserByLogin(username) ;
            UserDetailsImpl userDetails = new UserDetailsImpl(userByLogin) ;
            if (userDetails == null) {
                  throw new CustomRuntimeException(
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        ("Cant create userDetails in" +
                              this.getClass().getSimpleName())
                  ) ;
            }
            return userDetails;
      }
}
