package com.service.impl;

import com.entity.OrderEntity;
import com.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Override
    public List<OrderEntity> listByUserId(Integer userId) {
        List<OrderEntity> result = new ArrayList<>();

        OrderEntity entity = new OrderEntity();
        entity.setId(1);
        entity.setUserId("michael");
        entity.setCommodityCode("CommodityCode-12345");
        entity.setCount(1);
        entity.setAmount(10);

        result.add(entity);

        return result;
    }

}
