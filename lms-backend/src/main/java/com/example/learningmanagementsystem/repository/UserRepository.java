package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Role;
import com.example.learningmanagementsystem.entity.User;
import com.example.learningmanagementsystem.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByPhoneNumber(String phoneNumber);
//    Optional<User> findFirstByPhoneNumberAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(String phoneNumber);

    User findUserByParentKey(String key);

    @Query(nativeQuery = true, value = "select u.* from users u\n" +
            "inner join user_role ur on u.id = ur.user_id\n" +
            "inner join role r on r.id = ur.role_id\n" +
            "where r.role_enum=:name")
    User getByRoleName(String name);

//    User getByUserId(UUID uuid);

}
