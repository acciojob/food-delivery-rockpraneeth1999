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
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto order) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderEntity orderEntity = OrderTransformer.OrderDtoToOrderEntity(order);
        orderEntity = orderRepository.save(orderEntity);
        OrderDto orderDto = OrderTransformer.OrderEntityToOrderDto(orderEntity);

        return orderDto;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if(orderEntity==null)
            throw new Exception();
        OrderDto orderDto = OrderTransformer.OrderEntityToOrderDto(orderEntity);
        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if(orderEntity==null)
            throw new Exception();

//        String[] orderItems = order.getItems();
//        String[] items = new String[orderItems.length];
//        for(int i=0;i<orderItems.length;i++){
//            items[i]=orderItems[i];
//        }
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(order.getItems());
        orderEntity.setUserId(order.getUserId());

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