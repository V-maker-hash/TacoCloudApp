package com.example.tacocloud.jpaRepositories;

import com.example.tacocloud.domain.Order;
import com.example.tacocloud.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
