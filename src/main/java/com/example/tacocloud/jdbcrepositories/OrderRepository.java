package com.example.tacocloud.jdbcrepositories;

import com.example.tacocloud.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
