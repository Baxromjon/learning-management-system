package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query(nativeQuery = true, value = "select *\n" +
            "from task t\n" +
            "         inner join lesson l on t.lesson_id = l.id\n" +
            "         inner join groups g on l.group_id = g.id\n" +
            "         inner join courses c on g.course_id = c.id\n" +
            "where c.mentor_id_id = :mentorId")
    List<Task> getAllTaskByMentorId(UUID mentorId);
}
