package com.driver.transformer;

import com.driver.io.entity.UserEntity;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.UserDto;

import java.util.UUID;

public class UserTransformer {

    public static UserDto UserDetailsRequestModelToUserDto(UserDetailsRequestModel userDetailsRequestModel){
        UserDto userDto = new UserDto();
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setFirstName(userDetailsRequestModel.getFirstName());
        userDto.setLastName(userDetailsRequestModel.getLastName());
        userDto.setEmail(userDetailsRequestModel.getEmail());

        return userDto;
    }

    public static UserResponse UserDtoToUserResponse(UserDto userDto){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(userDto.getUserId());
        userResponse.setFirstName(userDto.getFirstName());
        userResponse.setLastName(userDto.getLastName());
        userResponse.setEmail(userDto.getEmail());

        return userResponse;
    }

    public static UserEntity UserDtoToUserEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userDto.getUserId());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());

        return userEntity;
    }

    public static UserDto UserEntityToUserDto(UserEntity userEntity){
        UserDto userDto = new UserDto();
        userDto.setUserId(userEntity.getUserId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());

        return userDto;
    }

    public static UserDto UserResponseToUserDto(UserResponse userResponse) {
        UserDto userDto = new UserDto();
        userDto.setId(userDto.getId());
        userDto.setUserId(userResponse.getUserId());
        userDto.setFirstName(userResponse.getFirstName());
        userDto.setLastName(userResponse.getLastName());
        userDto.setEmail(userResponse.getEmail());

        return userDto;
    }
}
