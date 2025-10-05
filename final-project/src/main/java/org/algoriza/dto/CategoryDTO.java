package org.algoriza.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class CategoryDTO {
    private String name;
    private List<String> brands;
    private List<String> sellers;

    public void addBrand(String brand){
        this.brands.add(brand);
    }
    public void addSeller(String seller){
        this.sellers.add(seller);
    }

}
