package com.example.learningmanagementsystem.repository;

import antlr.collections.List;
import com.example.learningmanagementsystem.entity.Role;
import com.example.learningmanagementsystem.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Set<Role> findByRoleEnum(RoleEnum nameEnum);

    @Query(nativeQuery = true, value = "select * from role\n" +
            "where role_enum!='ROLE_ADMIN' and role_enum!='ROLE_SUPER_ADMIN'")
    Set<Role> getAllUserRole();
}
