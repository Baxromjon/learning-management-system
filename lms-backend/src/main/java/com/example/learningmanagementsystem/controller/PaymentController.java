package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.PaymentDTO;
import com.example.learningmanagementsystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/add_payment")
    public HttpEntity<?> addPayment(@RequestBody PaymentDTO paymentDTO) {
        ApiResult add = paymentService.add(paymentDTO);
        return ResponseEntity.status(add.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(add);
    }
}
