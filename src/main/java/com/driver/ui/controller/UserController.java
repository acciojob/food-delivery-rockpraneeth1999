package com.driver.ui.controller;

import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
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

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = UserTransformer.UserDetailsRequestModelToUserDto(userDetails);
		try {
			userDto = userServiceImpl.createUser(userDto);
			UserResponse userResponse = UserTransformer.UserDtoToUserResponse(userDto);
			return userResponse;
		}
		catch (Exception exception){
			return null;
		}
	}


	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		try {
			UserDto userDto = userServiceImpl.getUserByUserId(id);
			UserResponse userResponse = UserTransformer.UserDtoToUserResponse(userDto);
			return userResponse;
		}
		catch (Exception exception){
			return new UserResponse();
		}
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		try {
			UserDto userDto = UserTransformer.UserDetailsRequestModelToUserDto(userDetails);
			userDto=userServiceImpl.updateUser(id,userDto);
			UserResponse userResponse = UserTransformer.UserDtoToUserResponse(userDto);
			return userResponse;
		}
		catch (Exception exception){
			return null;
		}
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
		try {
			userServiceImpl.deleteUser(id);
			OperationStatusModel operationStatusModel = new OperationStatusModel();
			operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
			operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
			return operationStatusModel;
		}
		catch (Exception exception){
			OperationStatusModel operationStatusModel = new OperationStatusModel();
			operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.toString());
			operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
			return operationStatusModel;
		}
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
