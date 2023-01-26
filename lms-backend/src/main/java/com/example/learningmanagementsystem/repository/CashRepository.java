package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Cash;
import com.example.learningmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CashRepository extends JpaRepository<Cash, UUID> {
    Cash findByUser(User user);

    @Query(nativeQuery = true, value = "select c.* from cash c\n" +
            "inner join users u on c.user_id = u.id\n" +
            "inner join groups_mentors gm on u.id = gm.mentors_id\n" +
            "where gm.groups_id=:groupId")
    Cash getMentorCashByGroupId(UUID groupId);

}
