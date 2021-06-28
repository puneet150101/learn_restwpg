package com.learn.restpg.Service;

import java.util.Arrays;
import java.util.stream.Collector;

import com.learn.restpg.Model.LoginInfo;
import com.learn.restpg.Model.UserInfo;
import com.learn.restpg.Repository.UserRepo;
import com.learn.restpg.Util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class LoginService {

	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private JdbcUserDetailsManager userDetailsService;

	public Outcome<?> genJwtOutcome(LoginInfo loginInfo) throws Exception {
		log.trace("Generating JWT token by given credentials.");
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword()));
			log.trace("Credentials Verified.");
		} catch (BadCredentialsException e) {
			log.warn("Incorrect credentials.", e);
			return Outcome.builder().status("Failed").response("Incorrect credentials").data("null").build();
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginInfo.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);
		log.trace("Generated Token is {}", jwt);
		return Outcome.builder().status("Success").response("Jwt Token Generated").data(jwt).build();
	}

	public Outcome<?> addUser(UserInfo userInfo) {
		try {
			log.trace("Adding user to the database.");
			userInfo.setRole("ROLE_" + (userInfo.getRole()).toUpperCase());
			userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
			userRepo.adduser(userInfo.getUsername(),userInfo.getPassword(),userInfo.getRole());
			userInfo.setPassword(null);
			return Outcome.builder().status("Success").response("User added").data(userInfo).build();
		} catch (Exception e) {
			log.error("Exception caught " + e.toString());
			return Outcome.builder().status("Failed").response("Error Occured").data(null).build();
		}
	}
}
