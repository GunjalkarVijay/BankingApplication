package com.security.bank.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.bank.dto.JwtRequest;
import com.security.bank.dto.JwtResponse;
import com.security.bank.dto.UserDto;
import com.security.bank.entity.Role;
import com.security.bank.entity.User;
import com.security.bank.jwt.JwtAuthenticationHelper;
import com.security.bank.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtAuthenticationHelper authenticationHelper;

	@Override
	public void register(UserDto dto) {
		User user = new User();
		user.setAccountList(dto.getAccoutList());
		user.setAddress(dto.getAddress());
		user.setIdentityProof(dto.getIdentityProof());
		user.setInvestmentList(dto.getInvestmentList());
		user.setName(dto.getName());
		user.setNumber(dto.getNumber());
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		user.setPassword(encodedPassword);
		user.setUsername(dto.getUsername());
		Role role = new Role();
		role.setRoleName("ROLE_CUSTOMER");
//		Set<Role> roles = user.getRoles();
//		roles.add(role);
		user.setRoles(role);
		userRepository.save(user);
		
	}

	@Override
	public JwtResponse login(JwtRequest request) {
		this.doAunthenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = authenticationHelper.generateToken(userDetails);
		JwtResponse response = JwtResponse.builder().jwtToken(token).build();
		return response;
	}
	
	public void doAunthenticate(String username, String password) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		try {
			authenticationManager.authenticate(token);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Username or Password");
		}
	}

}
