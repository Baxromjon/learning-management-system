package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.Course;
import com.example.learningmanagementsystem.entity.User;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.CourseDTO;
import com.example.learningmanagementsystem.repository.CourseRepository;
import com.example.learningmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    public ApiResult addCourse(CourseDTO courseDTO) {
        try {
            User user = userRepository.findById(courseDTO.getUserId()).orElseThrow();
            Course course = new Course(
                    courseDTO.getName(),
                    courseDTO.getDescription(),
                    user,
                    true, courseDTO.getLevel()
            );
            courseRepository.save(course);
            return new ApiResult(true, "Successfully add");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "Error in add course");
        }
    }

    public ApiResult getAll() {
        try {
            List<Course> all = courseRepository.findAll();
            return new ApiResult(all, true);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResult(false, "Error in get courses");
        }
    }
    public ApiResult getAllByMentorId(UUID mentorId) {
        try {
            User user = userRepository.findById(mentorId).orElseThrow();
            List<Course> courseList = courseRepository.findByMentorId(user);
            return new ApiResult(courseList, true);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResult(false, "Error in get courses");
        }
    }
}
