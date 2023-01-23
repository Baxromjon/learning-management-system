package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.entity.User;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.LoginDTO;
import com.example.learningmanagementsystem.payload.RegisterDTO;
import com.example.learningmanagementsystem.repository.UserRepository;
import com.example.learningmanagementsystem.security.JwtTokenProvider;
import com.example.learningmanagementsystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody RegisterDTO registerDTO){
        ApiResult register = authService.register(registerDTO);
        return ResponseEntity.status(register.getSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(register);
    }

    @PostMapping("/login")
    public HttpEntity<?> checkLogin(@RequestBody LoginDTO userDTO) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            userDTO.getPhoneNumber(),
                            userDTO.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            String token = jwtTokenProvider.generateToken(user.getId());
            return ResponseEntity.status(200).body(new ApiResult<>(token, true, "Successfully"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseEntity.status(409).body(new ApiResult<>(false, "Bad Credentials"));
    }

}
