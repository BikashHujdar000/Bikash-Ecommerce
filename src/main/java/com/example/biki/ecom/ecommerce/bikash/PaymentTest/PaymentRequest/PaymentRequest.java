package com.example.biki.ecom.ecommerce.bikash.PaymentTest.PaymentRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class PaymentRequest {

    @JsonProperty("return_url")
    private String returnUrl;

    @JsonProperty("website_url")
    private String websiteUrl;

    private int amount;

    @JsonProperty("purchase_order_id")
    private String purchaseOrderId;

    @JsonProperty("purchase_order_name")
    private String purchaseOrderName;

    @JsonProperty("customer_info")
    private CustomerInfo customerInfo;

    @JsonProperty("product_details")
    private List<ProductDetails> productDetails;

    @JsonProperty("merchant_username")
    private String merchantUsername;

}
