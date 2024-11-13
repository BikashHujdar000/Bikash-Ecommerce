package com.example.biki.ecom.ecommerce.bikash.PaymentTest.PaymentResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Response {

    @JsonProperty("payment_url")
    private String paymentUrl;

    @JsonProperty("expires_at")
    private OffsetDateTime expiresAt;


    private String pidx;

    @JsonProperty("expires_in")
    private long expiresIn;
}


