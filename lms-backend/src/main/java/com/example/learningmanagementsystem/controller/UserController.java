package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.entity.User;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.utils.CurrentUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/me")
    public HttpEntity<?> getUser(@CurrentUser User user){
        return ResponseEntity.ok(new ApiResult(user, true));
    }

}
