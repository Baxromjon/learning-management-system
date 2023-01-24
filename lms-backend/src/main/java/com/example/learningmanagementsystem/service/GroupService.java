package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.Course;
import com.example.learningmanagementsystem.entity.Group;
import com.example.learningmanagementsystem.entity.User;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.GroupDTO;
import com.example.learningmanagementsystem.repository.CourseRepository;
import com.example.learningmanagementsystem.repository.GroupRepository;
import com.example.learningmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    public ApiResult add(GroupDTO groupDTO) {
        try {
            Course course = courseRepository.findById(groupDTO.getCourseId()).orElseThrow();
            List<User> userList = userRepository.findAllById(groupDTO.getUserId());
            Group group = new Group(
                    groupDTO.getTitle(),
                    groupDTO.getDescription(),
                    groupDTO.getInviteLink(),
                    groupDTO.getTime(),
                    course,
                    groupDTO.getPrice(),
                    userList
            );
            groupRepository.save(group);
            return new ApiResult(true, "Successfully added");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "Error in Add group");
        }
    }

    public ApiResult getAll() {
        return new ApiResult(groupRepository.findAll(), true);
    }
}
