package com.driver.transformer;

import com.driver.io.entity.OrderEntity;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;

public class OrderTransformer {
    public static OrderDto OrderDetailsRequestModelToOrderDto(OrderDetailsRequestModel orderDetailsRequestModel) {
        OrderDto orderDto = new OrderDto();
//        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setCost(orderDetailsRequestModel.getCost());

//        String[] orderRequestDetailsModelItems = orderDetailsRequestModel.getItems();
//        String[] items = new String[orderRequestDetailsModelItems.length];
//        for(int i=0;i<orderRequestDetailsModelItems.length;i++){
//            items[i]=orderRequestDetailsModelItems[i];
//        }

        //orderDto.setItems(items);

        orderDto.setItems(orderDetailsRequestModel.getItems());
        orderDto.setUserId(orderDetailsRequestModel.getUserId());
        orderDto.setStatus(true);

        return orderDto;
    }

    public static OrderDetailsResponse OrderDtoToOrderDetailsResponse(OrderDto orderDto) {
        OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
        orderDetailsResponse.setOrderId(orderDto.getOrderId());
        orderDetailsResponse.setCost(orderDto.getCost());

//        String[] orderDtoItems = orderDetailsResponse.getItems();
//        String[] items = new String[orderDtoItems.length];
//        for(int i=0;i<orderDtoItems.length;i++){
//            items[i]=orderDtoItems[i];
//        }

        orderDetailsResponse.setItems(orderDto.getItems());
        orderDetailsResponse.setUserId(orderDto.getUserId());
        orderDetailsResponse.setStatus(orderDto.isStatus());

        return orderDetailsResponse;
    }

    public static OrderEntity OrderDtoToOrderEntity(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderDto.getOrderId());
        orderEntity.setCost(orderDto.getCost());

//        String[] orderDtoItems = orderDto.getItems();
//        String[] items = new String[orderDtoItems.length];
//        for(int i=0;i<orderDtoItems.length;i++){
//            items[i]=orderDtoItems[i];
//        }
//
//        orderEntity.setItems(items);

        orderEntity.setItems(orderDto.getItems());
        orderEntity.setUserId(orderDto.getUserId());
        orderEntity.setStatus(orderDto.isStatus());

        return orderEntity;
    }

    public static OrderDto OrderEntityToOrderDto(OrderEntity orderEntity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setOrderId(orderEntity.getOrderId());
        orderDto.setCost(orderEntity.getCost());

//        String[] orderEntityItems = orderEntity.getItems();
//        String[] items = new String[orderEntityItems.length];
//        for(int i=0;i<orderEntityItems.length;i++){
//            items[i]=orderEntityItems[i];
//        }
//
//        orderDto.setItems(items);

        orderDto.setItems(orderEntity.getItems());
        orderDto.setUserId(orderEntity.getUserId());
        orderDto.setStatus(orderEntity.isStatus());

        return orderDto;
    }
}
