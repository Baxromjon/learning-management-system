package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.OpenLesson;
import com.example.learningmanagementsystem.enums.OpenLessonEnumStatus;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.OpenLessonDTO;
import com.example.learningmanagementsystem.repository.OpenLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenLessonService {
    @Autowired
    OpenLessonRepository openLessonRepository;

    public ApiResult add(OpenLessonDTO openLessonDTO) {
        try {
            OpenLesson openLesson = new OpenLesson(
                    openLessonDTO.getTitle(),
                    openLessonDTO.isActive(),
                    openLessonDTO.getDescription(),
                    openLessonDTO.getSpeaker(),
                    openLessonDTO.getStartTime(),
                    openLessonDTO.getUrl(),
                    OpenLessonEnumStatus.NEW
            );
            openLessonRepository.save(openLesson);
            return new ApiResult(true, "Successfully added");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "Error in add Open Lesson");
        }
    }
}
