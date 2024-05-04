package edu.hillel.entities;

import java.util.Map;

import lombok.Data;

@Data
public class Cart {
    private Map<Long, Integer> addedItems;
    private User ownerOfCart;
}
