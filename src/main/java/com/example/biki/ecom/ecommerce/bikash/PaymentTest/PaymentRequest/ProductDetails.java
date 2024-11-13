package com.example.biki.ecom.ecommerce.bikash.PaymentTest.PaymentRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDetails {

    private String identity;
    private String name;

    @JsonProperty("total_price")
    private int totalPrice;

    private int quantity;

    @JsonProperty("unit_price")
    private int unitPrice;



}
