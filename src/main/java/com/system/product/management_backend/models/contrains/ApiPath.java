package com.system.product.management_backend.models.contrains;

public class ApiPath {

    /** Edit Product */
    public static final String EDIT = "/edit";

    /** User Controler */
    public static final String USER = "/user";

    /** Edit Product Introduce */
    public static final String GET_PRODUCT = "/getProduct/{code}";
    public static final String UPDATE_PRODUCT = "/updateProduct";
    public static final String GET_ALL_PRODUCT = "/getAllProduct";

    /** Edit Product Stock */
    public static final String UPDATE_STOCK_PRODUCT = "/updateStockProduct";
    public static final String GET_STOCK_PRODUCT = "/getStockProduct";
    public static final String DOWNLOAD_EXCEL = "/downloadProductExcel";
    public static final String DOWNLOAD_QR_CODE = "/downloadQRCode";


    /** User Login */
    public static final String LOGIN = "/login";
}
