package com.driver.ui.controller;

import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.service.impl.OrderServiceImpl;
import com.driver.shared.dto.OrderDto;
import com.driver.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderServiceImpl orderServiceImpl;

	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		OrderDto orderDto = OrderTransformer.OrderDetailsRequestModelToOrderDto(order);
		orderDto = orderServiceImpl.createOrder(orderDto);
		OrderDetailsResponse orderDetailsResponse = OrderTransformer.OrderDtoToOrderDetailsResponse(orderDto);

		return orderDetailsResponse;
	}

	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
		OrderDto orderDto = orderServiceImpl.getOrderById(id);
		OrderDetailsResponse orderDetailsResponse = OrderTransformer.OrderDtoToOrderDetailsResponse(orderDto);

		return orderDetailsResponse;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto orderDto = OrderTransformer.OrderDetailsRequestModelToOrderDto(order);
		orderDto = orderServiceImpl.updateOrderDetails(id,orderDto);
		OrderDetailsResponse orderDetailsResponse = OrderTransformer.OrderDtoToOrderDetailsResponse(orderDto);

		return orderDetailsResponse;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationResult("Success");
		operationStatusModel.setOperationName("Delete");
		orderServiceImpl.deleteOrder(id);

		return operationStatusModel;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDto> orderDtoList = orderServiceImpl.getOrders();
		List<OrderDetailsResponse> orderDetailsResponseList = new ArrayList<>();
		for(OrderDto orderDto:orderDtoList){
			orderDetailsResponseList.add(OrderTransformer.OrderDtoToOrderDetailsResponse(orderDto));
		}

		return orderDetailsResponseList;
	}
}
