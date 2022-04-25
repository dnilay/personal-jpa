package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.ui.UserRequestModel;
import com.example.demo.ui.UserResponseModel;
import com.example.dto.UserDto;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

	private final ModelMapper modelMapper;
	private final Environment environment;
	private final UserService userService;

	@RequestMapping
	public ResponseEntity<?> getStatus() {
		return ResponseEntity.ok("user-ws is up and running on port: " + environment.getProperty("local.server.port"));
	}

	@PostMapping(value="/users",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
	public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel userRequestModel) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);
		userDto.setUserId(UUID.randomUUID().toString());
		UserDto tempUserDto = userService.createUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(tempUserDto, UserResponseModel.class));

	}

	@GetMapping(value="/users", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<UserResponseModel>> getAllUsers() {
		List<UserDto> dtos = userService.getAllUsers();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<UserResponseModel> users = new ArrayList<UserResponseModel>();
		for (UserDto d : dtos) {
			users.add(modelMapper.map(d, UserResponseModel.class));
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping(value="/users/{userId}",produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<UserResponseModel> getUserByUserId(@PathVariable("userId") String userId) {
		UserEntity userEntity = userService.findUserByUserId(userId);
		if (userEntity == null) {
			throw new UserNotFoundException(userId);
		}
		return ResponseEntity.ok(modelMapper.map(userEntity, UserResponseModel.class));
	}

}
