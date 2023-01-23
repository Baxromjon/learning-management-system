package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.OpenLesson;
import com.example.learningmanagementsystem.entity.OpenLessonStudents;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.OpenLessonStudentsDTO;
import com.example.learningmanagementsystem.repository.OpenLessonRepository;
import com.example.learningmanagementsystem.repository.OpenLessonStudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenLessonStudentsService {

    @Autowired
    OpenLessonStudentsRepository openLessonStudentsRepository;

    @Autowired
    OpenLessonRepository openLessonRepository;


    public ApiResult add(OpenLessonStudentsDTO openLessonStudentsDTO) {
        try {
            OpenLesson openLesson = openLessonRepository.findById(openLessonStudentsDTO.getOpenLessonId()).orElseThrow();
            OpenLessonStudents openLessonStudents=new OpenLessonStudents(
                    openLessonStudentsDTO.getFullName(),
                    openLessonStudentsDTO.getPhoneNumber(),
                    openLesson,
                    openLessonStudentsDTO.getComment()
            );
            openLessonStudentsRepository.save(openLessonStudents);
            return new ApiResult(true, "Successfully added student to Open lesson");
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResult(false, "Error in add student");
        }
    }
}
