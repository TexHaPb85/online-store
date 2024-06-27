package edu.hillel.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class Item {
    private Long itemId;
    private String itemName;
    private String description;
    private Double price;
    private Float rate;
    private Integer instancesLeft;
    private Category category;

    public Item() {
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
