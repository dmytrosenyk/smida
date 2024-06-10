package org.senyk.smida.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.inMemoryAuthentication()
        .withUser("user").password("user").roles("USER")
        .and()
        .withUser("root").password("root").roles("USER", "ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/company/**", "/report/**").permitAll()
        .antMatchers(HttpMethod.PUT, "/company/**", "/report/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/company/**", "/report/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/company/**", "/report/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/report/company/**", "/report/details/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/report/details").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .csrf().disable()
        .formLogin();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {

    return NoOpPasswordEncoder.getInstance();
  }
}
