package com.example.learningmanagementsystem.controller;


import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.OpenLessonDTO;
import com.example.learningmanagementsystem.service.OpenLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/open_lesson")
public class OpenLessonController {
    @Autowired
    OpenLessonService openLessonService;

    @PostMapping("/add_open_lesson")
    public HttpEntity<?> addOpenLesson(@RequestBody OpenLessonDTO openLessonDTO) {
        ApiResult add = openLessonService.add(openLessonDTO);
        return ResponseEntity.status(add.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(add);
    }
}
