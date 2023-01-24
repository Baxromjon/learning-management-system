package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.LessonDTO;
import com.example.learningmanagementsystem.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {
    @Autowired
    LessonService lessonService;

    @PostMapping("/add_lesson")
    public HttpEntity<?> addLesson(@RequestBody LessonDTO lessonDTO) {
        ApiResult add = lessonService.add(lessonDTO);
        return ResponseEntity.status(add.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(add);
    }

    @GetMapping("/get_all_lesson")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(lessonService.getAll());
    }

    @GetMapping("/get_all_lesson_by_mentor/{mentorId}")
    public HttpEntity<?> getAllByMentor(@PathVariable UUID mentorId){
        return ResponseEntity.ok(lessonService.getAllByMentor(mentorId));
    }
}
