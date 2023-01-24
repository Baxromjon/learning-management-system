package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.Lesson;
import com.example.learningmanagementsystem.entity.Task;
import com.example.learningmanagementsystem.enums.EnumPriority;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.TaskDTO;
import com.example.learningmanagementsystem.repository.LessonRepository;
import com.example.learningmanagementsystem.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    LessonRepository lessonRepository;


    public ApiResult add(TaskDTO taskDTO) {
        try {
            Lesson lesson = lessonRepository.findById(taskDTO.getLessonId()).orElseThrow();
            Task task = new Task(
                    taskDTO.getTitle(),
                    taskDTO.getTaskNumber(),
                    lesson,
                    taskDTO.getUrl(),
                    EnumPriority.EASY,
                    true,
                    taskDTO.getDescription()
            );
            taskRepository.save(task);
            return new ApiResult(true, "Successfully added task");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "Error in add Task");
        }
    }

    public ApiResult getAll() {
        return new ApiResult(taskRepository.findAll(), true);
    }
}
