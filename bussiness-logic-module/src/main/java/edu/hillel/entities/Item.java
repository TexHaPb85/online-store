package edu.hillel.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Item {
    private Long itemId;
    private String itemName;
    private String description;
    private Double price;
    private Float rate;
    private Integer instancesLeft;

    @Override
    public String toString() {
        return String
            .join("_", itemId.toString(), itemName, description, price.toString(), rate.toString(), instancesLeft.toString());
    }
}
