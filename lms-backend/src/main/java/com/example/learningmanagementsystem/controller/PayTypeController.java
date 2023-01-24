package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.PayTypeDTO;
import com.example.learningmanagementsystem.service.PayTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payType")
public class PayTypeController {

    @Autowired
    PayTypeService payTypeService;

    @PostMapping("/add_payType")
    public HttpEntity<?> addPayType(@RequestBody PayTypeDTO payTypeDTO) {
        ApiResult add = payTypeService.add(payTypeDTO);
        return ResponseEntity.status(add.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(add);
    }
}
