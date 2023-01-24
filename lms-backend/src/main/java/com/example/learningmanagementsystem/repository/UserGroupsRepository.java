package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Group;
import com.example.learningmanagementsystem.entity.User;
import com.example.learningmanagementsystem.entity.UserGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserGroupsRepository extends JpaRepository<UserGroups, UUID> {

    Boolean existsByUserAndGroup(User user, Group group);
}
