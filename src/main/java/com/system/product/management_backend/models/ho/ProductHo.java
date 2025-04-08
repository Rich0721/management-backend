package com.system.product.management_backend.models.ho;

import java.util.List;

import com.system.product.management_backend.models.ho.basic.BasicProduct;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductHo extends BasicProduct {

    // Product ID
    private String code;

    // Proudct cost
    private int cost;

    // Product price
    private int price;

    // Product description
    private String description;

    // Product category
    private String category;

    // Display images
    private List<String> images;

    // Product content
    private String content;
}
