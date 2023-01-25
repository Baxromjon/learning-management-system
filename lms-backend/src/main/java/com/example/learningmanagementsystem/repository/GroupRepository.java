package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Course;
import com.example.learningmanagementsystem.entity.Group;
import com.example.learningmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {

//    List<Group> findByMentor(User mentors);

    @Query(nativeQuery = true, value = "select g.*\n" +
            "from groups g\n" +
            "         inner join courses c on g.course_id = c.id\n" +
            "where c.mentor_id_id = :mentorId")
    List<Group> getAllGroupByMentor(UUID mentorId);

    List<Group> findByCourse(Course course);

}
