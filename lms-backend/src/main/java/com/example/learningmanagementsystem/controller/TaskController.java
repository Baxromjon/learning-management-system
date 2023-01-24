package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.TaskDTO;
import com.example.learningmanagementsystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping("/add_task")
    public HttpEntity<?> addTask(@RequestBody TaskDTO taskDTO) {
        ApiResult add = taskService.add(taskDTO);
        return ResponseEntity.status(add.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(add);
    }

    @GetMapping("/get_all_task")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/get_all_task_by_mentor/{mentorId}")
    public HttpEntity<?> getAllByMentor(@PathVariable UUID mentorId){
        return ResponseEntity.ok(taskService.getAllByMentor(mentorId));
    }
}
