package org.platformlib.platformbackend.controller;


import org.platformlib.platformbackend.entity.Item;
import org.platformlib.platformbackend.repository.ItemRepository;
import org.platformlib.platformbackend.service.JwtService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class ItemController {
    @Autowired
    JwtService jwtService;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/api/items")
    public List<Item> getItems() {
        List<Item> items = itemRepository.findAll();

        return items;
    }
    @GetMapping("/api/items/add")
    public ResponseEntity<?> addItem(@CookieValue(value = "token", required = false) String token) {
        Logger logger = (Logger) LoggerFactory.getLogger(ItemController.class);

        if (!jwtService.isValid(token)) {
            logger.info("User not logged in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }

        logger.info("Item added successfully");
        return ResponseEntity.ok("Item added successfully");
    }
}
