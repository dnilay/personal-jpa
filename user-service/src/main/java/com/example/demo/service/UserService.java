package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UserEntity;
import com.example.dto.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto userDto);
	public List<UserDto> getAllUsers();
	
	public UserEntity findUserByUserId(String userId);

}
