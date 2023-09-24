package com.driver.ui.controller;

import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.UserResponse;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.UserDto;
import com.driver.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		UserDto userDto = userServiceImpl.getUser(id);
		UserResponse userResponse = UserTransformer.UserDtoToUserResponse(userDto);
		return userResponse;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = UserTransformer.UserDetailsRequestModelToUserDto(userDetails);
		userDto = userServiceImpl.createUser(userDto);
		UserResponse userResponse = UserTransformer.UserDtoToUserResponse(userDto);
		return userResponse;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = UserTransformer.UserDetailsRequestModelToUserDto(userDetails);
		userDto=userServiceImpl.updateUser(id,userDto);
		UserResponse userResponse = UserTransformer.UserDtoToUserResponse(userDto);
		return userResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
		userServiceImpl.deleteUser(id);
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationResult("Success");
		operationStatusModel.setOperationName("Delete");
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){
		List<UserDto> userDtoList = userServiceImpl.getUsers();
		List<UserResponse> userResponseList = new ArrayList<>();
		for(UserDto userDto:userDtoList){
			userResponseList.add(UserTransformer.UserDtoToUserResponse(userDto));
		}
		return userResponseList;
	}
	
}
