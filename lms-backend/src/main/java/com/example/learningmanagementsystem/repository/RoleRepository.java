package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Role;
import com.example.learningmanagementsystem.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Set<Role> findByRoleEnum(RoleEnum nameEnum);
}
