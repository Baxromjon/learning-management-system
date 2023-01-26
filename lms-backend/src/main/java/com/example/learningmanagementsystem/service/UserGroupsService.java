package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.*;
import com.example.learningmanagementsystem.enums.RoleEnum;
import com.example.learningmanagementsystem.enums.TaskStatusEnum;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.UserGroupsDTO;
import com.example.learningmanagementsystem.projection.TaskProjection;
import com.example.learningmanagementsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserGroupsService {
    @Autowired
    UserGroupsRepository userGroupsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    CashRepository cashRepository;

    @Autowired
    UserTaskRepository userTaskRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    TaskRepository taskRepository;

    public ApiResult add(UserGroupsDTO userGroupsDTO) {
        try {
            Optional<User> userOptional = userRepository.findById(userGroupsDTO.getUserId());
            User admin = userRepository.getByRoleName(RoleEnum.ROLE_ADMIN.toString());
            Cash adminCash = cashRepository.findByUser(admin);
            Optional<Group> groupOptional = groupRepository.findById(userGroupsDTO.getGroupId());
            if (!userOptional.isPresent()) {
                return new ApiResult(false, "User not found");
            }
            if (!groupOptional.isPresent()) {
                return new ApiResult(false, "Group not found");
            }
            Cash cash = cashRepository.findByUser(userOptional.get());
            Cash mentorCash = cashRepository.getMentorCashByGroupId(userGroupsDTO.getGroupId());
            if (groupOptional.get().getPrice() > cash.getAmount()) {
                return new ApiResult(false, "Your account does not have enough funds");
            }
            Boolean exists = userGroupsRepository.existsByUserAndGroup(userOptional.get(), groupOptional.get());
            if (exists) {
                return new ApiResult(false, "Group allready buyed");
            }
            List<TaskProjection> allTask = userTaskRepository.getAllTask(userGroupsDTO.getGroupId());

            UserGroups userGroups = new UserGroups();
            userGroups.setGroup(groupOptional.get());
            userGroups.setUser(userOptional.get());
            cash.setAmount(cash.getAmount() - groupOptional.get().getPrice());
            List<UserTask> userTasks = new ArrayList<>();
            for (int i = 0; i < allTask.size(); i++) {
                Attachment attachment = attachmentRepository.findById(allTask.get(i).getVideoId()).orElseThrow();
                Task task = taskRepository.findById(allTask.get(i).getId()).orElseThrow();
                UserTask userTask = new UserTask(
                        false,
                        0,
                        TaskStatusEnum.NEW,
                        allTask.get(i).getDescription(),
                        attachment,
                        task
                );
                userTasks.add(userTask);
            }
            userTaskRepository.saveAll(userTasks);
            userGroupsRepository.save(userGroups);
            cashRepository.save(cash);
            mentorCash.setAmount(groupOptional.get().getPrice() * 0.9);
            cashRepository.save(mentorCash);
            adminCash.setAmount(groupOptional.get().getPrice() * 0.1);
            cashRepository.save(adminCash);
            return new ApiResult(true, "Group Successfully buyed");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "Error in add user group");
        }
    }
}
