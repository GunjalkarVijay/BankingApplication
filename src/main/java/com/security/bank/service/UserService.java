package com.security.bank.service;

import com.security.bank.dto.JwtRequest;
import com.security.bank.dto.JwtResponse;
import com.security.bank.dto.UserDto;

public interface UserService {

	void register(UserDto dto);

	JwtResponse login(JwtRequest request);

}
