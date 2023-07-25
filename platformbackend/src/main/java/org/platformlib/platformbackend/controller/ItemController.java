package org.platformlib.platformbackend.controller;


import org.platformlib.platformbackend.entity.Item;
import org.platformlib.platformbackend.repository.ItemRepository;
import org.platformlib.platformbackend.service.JwtService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping("/api/items")
    // Extension of HttpEntity that adds an HttpStatusCode status code
    public ResponseEntity<Item> addItem(@RequestBody Item item,
                                        @CookieValue(value = "token", required = false) String token
    ) {
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Item savedItem = itemRepository.save(item);
        return ResponseEntity.ok(savedItem);
    }

    // The @PathVariable annotation to capture the dynamic value and assign it to a method parameter
    @DeleteMapping("/api/items/{itemId}")
    public ResponseEntity<String> deleteItem
    (@PathVariable int itemId, @CookieValue(value = "token", required = false) String token)
    {
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        // Check if the item with the specified ID exists in the database
        if (itemRepository.existsById(itemId)) {
            // Delete the item from the database
            Item item = itemRepository.findById(itemId);
            itemRepository.delete(item);
            return ResponseEntity.ok("Item deleted successfully");
        } else {
            // If the item doesn't exist, return an appropriate response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
    }
}
