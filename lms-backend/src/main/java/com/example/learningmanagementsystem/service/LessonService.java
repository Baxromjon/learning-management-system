package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.Course;
import com.example.learningmanagementsystem.entity.Lesson;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.LessonDTO;
import com.example.learningmanagementsystem.repository.AttachmentRepository;
import com.example.learningmanagementsystem.repository.CourseRepository;
import com.example.learningmanagementsystem.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    AttachmentRepository attachmentRepository;


    public ApiResult add(LessonDTO lessonDTO) {
        try {
            Course course = courseRepository.findById(lessonDTO.getCourseId()).orElseThrow();
            Lesson lesson = new Lesson(
                    lessonDTO.getTitle(),
                    lessonDTO.getLessonNumber(),
                    course,
                    lessonDTO.getUrl(),
                    attachmentRepository.findById(lessonDTO.getVideoId()).orElseThrow()
            );
            lessonRepository.save(lesson);
            return new ApiResult(true, "Successfully added");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "Error in ad  lesson");
        }
    }
}
