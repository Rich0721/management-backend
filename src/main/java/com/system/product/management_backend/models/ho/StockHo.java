package com.system.product.management_backend.models.ho;

import com.system.product.management_backend.models.ho.basic.BasicProduct;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockHo extends BasicProduct {

    private int stockNumbers;

    private String category;
}
