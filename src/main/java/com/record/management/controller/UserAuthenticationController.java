package com.record.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.record.management.dto.AuthnticateRequest;
import com.record.management.dto.AuthnticateResponse;
import com.record.management.dto.UserDto;
import com.record.management.service.UserService;
import com.record.management.service.impl.CustomUserDetailServiceImpl;
import com.record.management.service.impl.JwtTokenServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/user")
@Api(description = "Controller authenticate user.")
public class UserAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailServiceImpl customUserDetailService;

	@Autowired
	private JwtTokenServiceImpl jwtUtil;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(code = 200, response = AuthnticateResponse.class, value = "authenticate user based on user name and password.")
	@PostMapping("/authenticate")
	public ResponseEntity<AuthnticateResponse> authenticate(@RequestBody AuthnticateRequest authnticateRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authnticateRequest.getUsername(),
					authnticateRequest.getPassword()));
		} catch (BadCredentialsException ex) {
			new BadCredentialsException("Entered username and password is not correct. Please try again.");
		}

		final UserDetails userDetails = customUserDetailService.loadUserByUsername(authnticateRequest.getUsername());
		final String token = jwtUtil.generateToken(userDetails);
		return new ResponseEntity<AuthnticateResponse>(new AuthnticateResponse(token), HttpStatus.OK);
	}
	
	@ApiOperation(code = 200, response = UserDto.class, value = "Create the admin user and admin role for first time.")
	@PostMapping("/registerAdminUser")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) throws Exception {
		return new ResponseEntity<UserDto>(userService.addAdminUserDetails(user), HttpStatus.CREATED);
	}

}
