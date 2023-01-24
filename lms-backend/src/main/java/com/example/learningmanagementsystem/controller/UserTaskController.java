package com.example.learningmanagementsystem.controller;


import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.UserTaskDTO;
import com.example.learningmanagementsystem.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user_task")
public class UserTaskController {
    @Autowired
    UserTaskService userTaskService;

    @PostMapping("/add_user_task")
    public HttpEntity<?> addUserTask(@RequestBody UserTaskDTO userTaskDTO) {
        ApiResult add = userTaskService.add(userTaskDTO);
        return ResponseEntity.status(add.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(add);
    }
}
