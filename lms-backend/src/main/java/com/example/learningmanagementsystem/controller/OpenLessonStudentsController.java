package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.OpenLessonStudentsDTO;
import com.example.learningmanagementsystem.service.OpenLessonStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/open_lesson_students")
public class OpenLessonStudentsController {
    @Autowired
    OpenLessonStudentsService openLessonStudentsService;

    @PostMapping("/add_students")
    public HttpEntity<?> addStudents(@RequestBody OpenLessonStudentsDTO openLessonStudentsDTO) {
        ApiResult add = openLessonStudentsService.add(openLessonStudentsDTO);
        return ResponseEntity.status(add.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(add);
    }
}
