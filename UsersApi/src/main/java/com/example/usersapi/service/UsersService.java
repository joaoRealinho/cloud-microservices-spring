package com.example.usersapi.service;


import com.example.usersapi.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
	UserDto createUser(UserDto userDetails);

	UserDto getUserDetailsByEmail(String email);
}
