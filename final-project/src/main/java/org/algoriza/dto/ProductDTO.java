package org.algoriza.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class ProductDTO {

    @Setter
    private String category;
    @Setter
    private String title;
    @Setter
    private int quantity;

    private Map<String,String> properties;


    public void addProperty(String key, String value){
        properties.put(key, value);
    }
    public void removeProperty(String key){
        properties.remove(key);
    }

    @Override
    public String toString() {
        String formattedProperties = properties.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .reduce((a, b) -> a + ", " + b)
                .orElse("None");

        return String.format("Product: %s | Category: %s | Quantity: %d | Properties: %s",
                title, category, quantity, formattedProperties);
    }
}
