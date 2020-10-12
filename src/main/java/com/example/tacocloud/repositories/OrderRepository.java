package com.example.tacocloud.repositories;

import com.example.tacocloud.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
