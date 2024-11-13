package com.example.biki.ecom.ecommerce.bikash.PaymentTest;
import com.example.biki.ecom.ecommerce.bikash.PaymentTest.PaymentRequest.CustomerInfo;
import com.example.biki.ecom.ecommerce.bikash.PaymentTest.PaymentRequest.PaymentRequest;
import com.example.biki.ecom.ecommerce.bikash.PaymentTest.PaymentRequest.ProductDetails;
import com.example.biki.ecom.ecommerce.bikash.PaymentTest.PaymentResponse.Response;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private RestClient restClient;


    @PostMapping("/payment")
    public ResponseEntity<Response>paymentTest()
    {



        CustomerInfo customerInfo = new CustomerInfo();

        customerInfo.setName("Bikash");
        customerInfo.setPhone("9810209126");
        customerInfo.setEmail("hujdar@gmail.com");

        List<ProductDetails> newList = new ArrayList<>();


        ProductDetails productDetails1 = new ProductDetails();
        productDetails1.setIdentity("2");
        productDetails1.setQuantity(2);
        productDetails1.setName("Test_Product");
        productDetails1.setTotalPrice(300);
        productDetails1.setUnitPrice(2);


        ProductDetails productDetails2 = new ProductDetails();
        productDetails2.setIdentity("2");
        productDetails2.setQuantity(2);
        productDetails2.setName("Test_Product");
        productDetails2.setTotalPrice(300);
        productDetails2.setUnitPrice(2);

        newList.add(productDetails1);
        newList.add(productDetails2);


        PaymentRequest request = new PaymentRequest();


        request.setReturnUrl("https://example.com/payment/");
        request.setWebsiteUrl("https://example.com/");
        request.setAmount(3000);

        request.setPurchaseOrderId("test!2");

        request.setPurchaseOrderName("Test");

        request.setCustomerInfo(customerInfo);



        request.setProductDetails(newList);



        request.setMerchantUsername("Bikash Hujdar Khalti");


        System.out.println(request);

        logger.info("Payment Request: {}", request);  // Log the request



        Response response = null;


        response = restClient.post()
                .uri("/epayment/initiate/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Key ec63b5319530417e9a6e38db5e85ab17")
                .body(request)
                .retrieve()
                .body(Response.class);



        return  new ResponseEntity<>(response,HttpStatus.ACCEPTED);

    }

}