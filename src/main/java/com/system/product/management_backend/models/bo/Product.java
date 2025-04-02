package com.system.product.management_backend.models.bo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {

    // Product ID
    private String code;

    // Product price
    private int price;
    
    // Product name
    private String name;

    // Product description
    private String description;

    // Product category
    private String category;

    // Display images
    private List<String> images;

    // Product content
    private String content;
}
