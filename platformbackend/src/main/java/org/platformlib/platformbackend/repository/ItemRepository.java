package org.platformlib.platformbackend.repository;

import org.platformlib.platformbackend.entity.Cart;
import org.platformlib.platformbackend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByIdIn(List<Integer> ids);
    Item findById(int memberId);
    boolean existsById(int itemId);
}
