package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import com.driver.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity = OrderTransformer.OrderDtoToOrderEntity(order);
        orderEntity = orderRepository.save(orderEntity);
        OrderDto orderDto = OrderTransformer.OrderEntityToOrderDto(orderEntity);

        return orderDto;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        OrderDto orderDto = OrderTransformer.OrderEntityToOrderDto(orderEntity);

        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setCost(order.getCost());

        String[] orderItems = order.getItems();
        String[] items = new String[orderItems.length];
        for(int i=0;i<orderItems.length;i++){
            items[i]=orderItems[i];
        }

        orderEntity.setItems(items);
        orderEntity.setUserId(order.getUserId());
        orderEntity.setStatus(order.isStatus());

        orderEntity = orderRepository.save(orderEntity);

        OrderDto orderDto = OrderTransformer.OrderEntityToOrderDto(orderEntity);

        return orderDto;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if(orderEntity == null){
            throw new Exception("Invalid orderId");
        }

        orderRepository.delete(orderEntity);
    }

    @Override
    public List<OrderDto> getOrders() {
        Iterable<OrderEntity> iterable = orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for(OrderEntity orderEntity:iterable){
            orderDtoList.add(OrderTransformer.OrderEntityToOrderDto(orderEntity));
        }

        return orderDtoList;
    }
}