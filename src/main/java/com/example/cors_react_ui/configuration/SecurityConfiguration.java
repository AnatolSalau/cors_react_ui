package com.example.cors_react_ui.configuration;

import com.example.cors_react_ui.services.UserDetailslServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 *    Security configuration with CSRF token
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

      @Autowired
      UserDetailslServiceImpl userDetailslService;

      @Value("${websecurity.debug}")
      boolean webSecurityDebug;

      // Security debugging is enabled.
      @Bean
      public WebSecurityCustomizer
      webSecurityCustomizer() {
            return (web) -> web.debug(webSecurityDebug);
      }

      @Bean
      public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                  @Override
                  public void addCorsMappings(CorsRegistry registry) {
                        registry
                              .addMapping("/**")
                              .allowedOrigins("http://localhost:3000/")
/*                              .allowCredentials(true)*/
                        ;
                  }
            };
      }


      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
            tokenRepository.setHeaderName("X-XSRF-TOKEN");
            XorCsrfTokenRequestAttributeHandler delegate = new XorCsrfTokenRequestAttributeHandler();
            // set the name of the attribute the CsrfToken will be populated on
            delegate.setCsrfRequestAttributeName("_csrf");
            // Use only the handle() method of XorCsrfTokenRequestAttributeHandler and the
            // default implementation of resolveCsrfTokenValue() from CsrfTokenRequestHandler
            CsrfTokenRequestHandler requestHandler = delegate::handle;

            http
                  .authorizeHttpRequests()
                  .requestMatchers(
                        "/"
                  )
                  .permitAll()
                  .and()
                  .authorizeHttpRequests()
                  .requestMatchers(
                        "/api/v1/users",
                        "/api/v1/admins",
                        "/api/v1/document",
                        "/api/v1/document/**",
                        "/authenticate"
                  )
                  .authenticated()
                  .anyRequest().denyAll()
                  .and()
                  .csrf((csrf) -> csrf
                        .csrfTokenRepository(tokenRepository)
                        .csrfTokenRequestHandler(requestHandler)
                  )
/*                  .csrf((csrf) -> csrf
                        .disable()
                  )*/
                  .cors()
                  .and()
                  .httpBasic(Customizer.withDefaults());

            return http.build();
      }

      //Encoder for encode passwords from DB
      @Bean
      public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
      }

      @Bean
      public AuthenticationProvider authenticationProvider(UserDetailslServiceImpl userDetailsService) {
            DaoAuthenticationProvider authenticationProvider = new
                  DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userDetailsService);
            authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
            return authenticationProvider;
      }
}
