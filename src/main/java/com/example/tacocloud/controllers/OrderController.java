package com.example.tacocloud.controllers;

import com.example.tacocloud.domain.Order;
import com.example.tacocloud.domain.User;
import com.example.tacocloud.jpaRepositories.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.awt.print.Pageable;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepository;
    private int pageSize = 20;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
                            @ModelAttribute Order order) {
        if (order.getName() == null) {
            order.setName(user.getFullname());
        }
        if (order.getStreet() == null) {
            order.setStreet(user.getStreet());
        }
        if (order.getCity() == null) {
            order.setCity(user.getCity());
        }
        if (order.getState() == null) {
            order.setState(user.getState());
        }
        if (order.getZip() == null) {
            order.setZip(user.getZip());
        }

        return "orderForm";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user,
                                Model model) {
        Pageable pageable = (Pageable) PageRequest.of(0, pageSize);
        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

}
