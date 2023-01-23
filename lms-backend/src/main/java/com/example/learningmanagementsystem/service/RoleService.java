package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.Role;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public ApiResult getAll() {
        try {
            List<Role> all = roleRepository.findAll();
            return new ApiResult(all, true);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResult(false, "Error in get all roles");
        }
    }
}
