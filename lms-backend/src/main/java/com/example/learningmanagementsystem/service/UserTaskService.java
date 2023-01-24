package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.UserTaskDTO;
import com.example.learningmanagementsystem.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTaskService {
    @Autowired
    UserTaskRepository userTaskRepository;

    public ApiResult add(UserTaskDTO userTaskDTO) {
        try {
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResult(false, "Error in add User Task");
        }
    }
}
