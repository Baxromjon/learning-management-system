package com.example.learningmanagementsystem.controller;

import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.GroupDTO;
import com.example.learningmanagementsystem.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    GroupService groupService;

    @PostMapping("/add_group")
    public HttpEntity<?> addGroup(@RequestBody GroupDTO groupDTO) {
        ApiResult add = groupService.add(groupDTO);
        return ResponseEntity.status(add.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(add);
    }

    @GetMapping("/get_all_group")
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok(groupService.getAll());
    }
}
