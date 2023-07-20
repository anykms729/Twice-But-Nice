package org.platformlib.platformbackend.dto;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class OrderDto {
    private String name;
    private String address;
    private String payment;
    private String cardNumber;
    private String items;
}
