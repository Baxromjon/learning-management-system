package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.entity.Cash;
import com.example.learningmanagementsystem.entity.User;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.repository.CashRepository;
import com.example.learningmanagementsystem.repository.UserRepository;
import com.example.learningmanagementsystem.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    CashRepository cashRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/me")
    public HttpEntity<?> getUser(@CurrentUser User user) {
        return ResponseEntity.ok(new ApiResult(user, true));
    }

    @GetMapping("/get_cash_by_user/{userId}")
    public HttpEntity<?> getCash(@PathVariable UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Cash cash = cashRepository.findByUser(user);
        return ResponseEntity.ok(cash);
    }

    @GetMapping("/get_user_by_id/{userId}")
    public HttpEntity<?> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok(userRepository.findById(userId).orElseThrow());
    }

}
