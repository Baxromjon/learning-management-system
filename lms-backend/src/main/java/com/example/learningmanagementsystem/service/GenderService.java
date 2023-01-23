package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.Gender;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderService {

    @Autowired
    GenderRepository genderRepository;

    public ApiResult getAll() {
        try {
            List<Gender> all = genderRepository.findAll();
            return new ApiResult(all, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "Error in get all Gender");
        }
    }
}
