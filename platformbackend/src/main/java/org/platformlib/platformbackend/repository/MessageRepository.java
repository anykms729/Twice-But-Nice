package org.platformlib.platformbackend.repository;

import org.platformlib.platformbackend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findBySender(String sender);
}
