package com.service;

import com.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    List<OrderEntity> listByUserId(Integer userId);
}