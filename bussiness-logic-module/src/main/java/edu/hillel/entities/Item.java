package edu.hillel.entities;

import lombok.Builder;

@Builder
public class Item {
    private Long itemId;
    private String itemName;
    private String description;
    private Double price;
    private Float rate;
    private Integer instancesLeft;

    public Item() {
    }

    public Item(Long itemId, String itemName, String description, Double price, Float rate, Integer instancesLeft) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.rate = rate;
        this.instancesLeft = instancesLeft;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Integer getInstancesLeft() {
        return instancesLeft;
    }

    public void setInstancesLeft(Integer instancesLeft) {
        this.instancesLeft = instancesLeft;
    }

    @Override
    public String toString() {
        return String
            .join("_", itemId.toString(), itemName, description, price.toString(), rate.toString(), instancesLeft.toString());
    }
}
