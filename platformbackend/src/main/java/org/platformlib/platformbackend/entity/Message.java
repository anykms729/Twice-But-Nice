package org.platformlib.platformbackend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 600)
    private String items;

    @Column(length = 10)
    private String negotiationPrice;

    @Column(length = 50, nullable = false)
    private String sender;

    @Column(length = 500, nullable = false)
    private String text;
}
