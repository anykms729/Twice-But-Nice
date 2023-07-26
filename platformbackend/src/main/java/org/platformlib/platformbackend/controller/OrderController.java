package org.platformlib.platformbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.platformlib.platformbackend.dto.OrderDto;
import org.platformlib.platformbackend.entity.Order;
import org.platformlib.platformbackend.repository.CartRepository;
import org.platformlib.platformbackend.repository.OrderRepository;
import org.platformlib.platformbackend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class OrderController {

    private static final Logger LOGGER = Logger.getLogger(OrderController.class.getName());

    @Autowired
    JwtService jwtService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @GetMapping("/api/orders")
    public ResponseEntity getOrder(
            @CookieValue(value = "token", required = false) String token
    ) {
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId = jwtService.getId(token);
        List<Order> orders = orderRepository.findByMemberIdOrderByIdDesc(memberId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     POST when you want to create a new resource on the server, and PUT when you want to update an existing resource. However, the specific use cases for each method may vary depending on the requirements of your application.
     */
    @Transactional
    @PostMapping("/api/orders")
    public ResponseEntity pushOrder(
            @RequestBody OrderDto dto,
            @CookieValue(value = "token", required = false) String token
    ) throws JsonProcessingException {
        LOGGER.info("pushOrder method called.");

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        Order newOrder = new Order();
        newOrder.setMemberId(memberId);
        newOrder.setName(dto.getName());
        newOrder.setAddress(dto.getAddress());
        newOrder.setPayment(dto.getPayment());
        newOrder.setCardNumber(dto.getCardNumber());
        newOrder.setItems(dto.getItems());


        try {
            orderRepository.save(newOrder);
            cartRepository.deleteByMemberId(memberId);

            LOGGER.info("pushOrder method completed successfully.");

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.severe("An error occurred during pushOrder: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process order.");
        }
    }
}
