package com.system.product.management_backend.utils;

import java.time.Instant;

public class ProductUtils {
    
    public static String generateProductCode(){
        long unixTime = Instant.now().toEpochMilli();

        return String.format("%013d", unixTime);
    }
}
