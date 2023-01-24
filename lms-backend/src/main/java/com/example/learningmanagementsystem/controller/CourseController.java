package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.CourseDTO;
import com.example.learningmanagementsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/add_course")
    public HttpEntity<?> addCourse(@RequestBody CourseDTO courseDTO) {
        ApiResult apiResult = courseService.addCourse(courseDTO);
        return ResponseEntity.status(apiResult.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResult);
    }

    @GetMapping("/get_all_courses")
    public HttpEntity<?> getAll() {
        ApiResult all = courseService.getAll();
        return ResponseEntity.status(all.getSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(all);
    }
    @GetMapping("/get_all_courses_by_mentor/{mentorId}")
    public HttpEntity<?> getAll(@PathVariable UUID mentorId) {
        ApiResult all = courseService.getAllByMentorId(mentorId);
        return ResponseEntity.status(all.getSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(all);
    }

}
