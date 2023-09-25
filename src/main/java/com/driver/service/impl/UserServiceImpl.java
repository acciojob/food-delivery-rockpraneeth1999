package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.model.response.UserResponse;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import com.driver.transformer.UserTransformer;
import com.driver.ui.controller.AuthenticationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity userEntity = UserTransformer.UserDtoToUserEntity(user);
        userEntity=userRepository.save(userEntity);

        if(userEntity==null)
            throw new Exception("Unable to add User");

        UserDto userDto = UserTransformer.UserEntityToUserDto(userEntity);
        return userDto;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserResponse userResponse = authenticationController.getUserByEmail(email);
        UserDto userDto = UserTransformer.UserResponseToUserDto(userResponse);
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity==null)
            throw new Exception("Invalid user id");
        UserDto userDto=UserTransformer.UserEntityToUserDto(userEntity);
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity==null)
            throw new Exception("Invalid user id");

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());

        userEntity=userRepository.save(userEntity);
        UserDto userDto = UserTransformer.UserEntityToUserDto(userEntity);
        return userDto;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity==null)
            throw new Exception("Invalid user id");

        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers() {
        Iterable<UserEntity> iterable = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(UserEntity userEntity:iterable){
            userDtoList.add(UserTransformer.UserEntityToUserDto(userEntity));
        }

        return userDtoList;
    }
}