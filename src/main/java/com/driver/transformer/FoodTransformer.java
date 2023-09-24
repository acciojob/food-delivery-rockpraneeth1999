package com.driver.transformer;

import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;

import java.util.UUID;

public class FoodTransformer {
    public static FoodDto CreateFoodDtoFromFoodDetailsRequestModel(FoodDetailsRequestModel foodDetailsRequestModel) {
        FoodDto foodDto = new FoodDto();
        foodDto.setFoodId(UUID.randomUUID().toString());
        foodDto.setFoodName(foodDetailsRequestModel.getFoodName());
        foodDto.setFoodCategory(foodDetailsRequestModel.getFoodCategory());
        foodDto.setFoodPrice(foodDetailsRequestModel.getFoodPrice());

        return foodDto;
    }

    public static FoodDetailsResponse FoodDtoToFoodDetailsResponse(FoodDto foodDto) {
        FoodDetailsResponse foodDetailsResponse=new FoodDetailsResponse();
        foodDetailsResponse.setFoodId(foodDto.getFoodId());
        foodDetailsResponse.setFoodName(foodDto.getFoodName());
        foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());
        foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());

        return foodDetailsResponse;
    }

    public static FoodEntity FoodDtoToFoodEntity(FoodDto foodDto) {
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setFoodId(foodDto.getFoodId());
        foodEntity.setFoodName(foodDto.getFoodName());
        foodEntity.setFoodCategory(foodDto.getFoodCategory());
        foodEntity.setFoodPrice(foodDto.getFoodPrice());

        return foodEntity;
    }

    public static FoodDto FoodEntityToFoodDto(FoodEntity foodEntity) {
        FoodDto foodDto = new FoodDto();
        foodDto.setId(foodEntity.getId());
        foodDto.setFoodId(foodEntity.getFoodId());
        foodDto.setFoodName(foodEntity.getFoodName());
        foodDto.setFoodCategory(foodEntity.getFoodCategory());
        foodDto.setFoodPrice(foodEntity.getFoodPrice());

        return foodDto;
    }
}
