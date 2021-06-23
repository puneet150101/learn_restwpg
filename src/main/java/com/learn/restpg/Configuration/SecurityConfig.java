package com.learn.restpg.Configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.trace("Setting JDBC datasource for authentication.");
        auth.jdbcAuthentication()
        .dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.trace("Authorizing HTTP requests.");
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET,"/students").hasAnyRole("USER","ADMIN")
        .antMatchers(HttpMethod.GET,"/student/*").hasAnyRole("USER","ADMIN")
        .antMatchers("/**").hasRole("ADMIN")
        .and().formLogin();
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
