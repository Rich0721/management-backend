package com.system.product.management_backend.utils;

import java.time.Instant;

public class ProductUtils {
    
    public static String generateProductCode(){
        long unixTime = Instant.now().getEpochSecond();

        return String.format("%012d", unixTime);
    }
}
