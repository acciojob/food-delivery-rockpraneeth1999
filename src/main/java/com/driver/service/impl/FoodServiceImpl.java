package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import com.driver.transformer.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;
    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity = FoodTransformer.FoodDtoToFoodEntity(food);
        foodEntity = foodRepository.save(foodEntity);
        FoodDto foodDto = FoodTransformer.FoodEntityToFoodDto(foodEntity);
        return foodDto;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        if(foodEntity==null)
            throw new Exception("Invalid food id");

        FoodDto foodDto = FoodTransformer.FoodEntityToFoodDto(foodEntity);
        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        if (foodEntity==null)
            throw new Exception("Invalid food id");

        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodCategory(foodDetails.getFoodCategory());
        foodEntity.setFoodPrice(foodDetails.getFoodPrice());

        foodEntity=foodRepository.save(foodEntity);
        FoodDto foodDto=FoodTransformer.FoodEntityToFoodDto(foodEntity);
        return foodDto;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(id);
        if (foodEntity == null) {
            throw new Exception();
        }
        foodRepository.delete(foodEntity);
    }

    @Override
    public List<FoodDto> getFoods() {
        Iterable<FoodEntity> iterable = foodRepository.findAll();
        List<FoodDto> foodDtoList = new ArrayList<>();
        for(FoodEntity foodEntity:iterable){
            foodDtoList.add(FoodTransformer.FoodEntityToFoodDto(foodEntity));
        }
        return foodDtoList;
    }
}