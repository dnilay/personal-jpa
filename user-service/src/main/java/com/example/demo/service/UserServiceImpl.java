package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.dto.UserDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		StringBuffer buffer = new StringBuffer(userDto.getPassword());
		userEntity.setEncryptedPassword(buffer.reverse().toString());
		UserEntity tempUserEntity = userRepository.save(userEntity);

		return modelMapper.map(tempUserEntity, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<UserEntity> list = userRepository.findAll();
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (UserEntity e : list) {
			dtos.add(modelMapper.map(e, UserDto.class));
		}
		return dtos;
	}

	@Override
	public UserEntity findUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null) {
			throw new UserNotFoundException("user not found");
		}

		return userEntity;
	}

}
