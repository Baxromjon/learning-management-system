package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.Course;
import com.example.learningmanagementsystem.entity.Group;
import com.example.learningmanagementsystem.entity.Lesson;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.LessonDTO;
import com.example.learningmanagementsystem.repository.AttachmentRepository;
import com.example.learningmanagementsystem.repository.CourseRepository;
import com.example.learningmanagementsystem.repository.GroupRepository;
import com.example.learningmanagementsystem.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    AttachmentRepository attachmentRepository;


    public ApiResult add(LessonDTO lessonDTO) {
        try {
            Group group = groupRepository.findById(lessonDTO.getGroupId()).orElseThrow();
            Lesson lesson = new Lesson(
                    lessonDTO.getTitle(),
                    lessonDTO.getLessonNumber(),
                    group,
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

    public ApiResult getAll() {
        return new ApiResult(lessonRepository.findAll(), true);
    }

    public ApiResult getAllByMentor(UUID mentorId) {
        try {
            List<Lesson> lessonList = lessonRepository.getAllByMentorId(mentorId);
            return new ApiResult(lessonList, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "Error in get by Id");
        }
    }
}
