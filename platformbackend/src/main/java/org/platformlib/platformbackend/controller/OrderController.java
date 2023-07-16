package org.platformlib.platformbackend.controller;

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

    /**
     * Get the orders for the authenticated members
     * @param token the authentication token
     * @return a list of orders
     */

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
     * Push a new order for the authenticated members
     * @param dto the order data
     * @param token the authentication token
     * @return a response entity indicating success or failure
     */

    /**
    The @Transactional annotation is used in Spring to indicate that a method should be executed within a transaction. A transaction is a unit of work that is performed on a database, and it ensures that all changes made to the database within the transaction are either committed (saved permanently) or rolled back (undone) if an error occurs.
    */

    /**
     POST when you want to create a new resource on the server, and PUT when you want to update an existing resource. However, the specific use cases for each method may vary depending on the requirements of your application.
     */
    @Transactional
    @PostMapping("/api/orders")
    public ResponseEntity pushOrder(
            @RequestBody OrderDto dto,
            @CookieValue(value = "token", required = false) String token
    ) {
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
            System.out.println("pushOrder method completed successfully.");
            LOGGER.severe("An error occurred during pushOrder: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process order.");
        }
    }
}
