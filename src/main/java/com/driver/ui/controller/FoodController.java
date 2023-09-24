package com.driver.ui.controller;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
import com.driver.transformer.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodServiceImpl foodServiceImpl;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto foodDto = foodServiceImpl.getFoodById(id);
		FoodDetailsResponse foodDetailsResponse = FoodTransformer.FoodDtoToFoodDetailsResponse(foodDto);
		return foodDetailsResponse;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		FoodDto foodDto = FoodTransformer.CreateFoodDtoFromFoodDetailsRequestModel(foodDetails);
		foodDto = foodServiceImpl.createFood(foodDto);
		FoodDetailsResponse foodDetailsResponse = FoodTransformer.FoodDtoToFoodDetailsResponse(foodDto);
		return foodDetailsResponse;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto = FoodTransformer.CreateFoodDtoFromFoodDetailsRequestModel(foodDetails);
		foodDto = foodServiceImpl.updateFoodDetails(id,foodDto);
		FoodDetailsResponse foodDetailsResponse = FoodTransformer.FoodDtoToFoodDetailsResponse(foodDto);
		return foodDetailsResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		OperationStatusModel operationStatusModel = new OperationStatusModel();

		try {
			foodServiceImpl.deleteFoodItem(id);
			operationStatusModel.setOperationName("deleteFood");
			operationStatusModel.setOperationResult("Success");
		} catch (Exception e) {
			operationStatusModel.setOperationName("deleteFood");
			operationStatusModel.setOperationResult("Failure");
		}

		return operationStatusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDto> foodDtoList= foodServiceImpl.getFoods();
		List<FoodDetailsResponse> foodDetailsResponseList = new ArrayList<>();
		for(FoodDto foodDto:foodDtoList){
			foodDetailsResponseList.add(FoodTransformer.FoodDtoToFoodDetailsResponse(foodDto));
		}
		return foodDetailsResponseList;
	}
}
