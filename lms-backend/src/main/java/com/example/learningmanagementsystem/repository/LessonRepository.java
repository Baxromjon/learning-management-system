package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {

    @Query(nativeQuery = true, value = "select lesson.*\n" +
            "from lesson\n" +
            "         inner join groups g on lesson.group_id = g.id\n" +
            "         inner join courses c on g.course_id = c.id\n" +
            "where c.mentor_id_id = :mentorId")
    List<Lesson> getAllByMentorId(UUID mentorId);

}
