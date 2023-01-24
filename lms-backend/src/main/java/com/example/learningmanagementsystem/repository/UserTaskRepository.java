package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Task;
import com.example.learningmanagementsystem.entity.UserTask;
import com.example.learningmanagementsystem.projection.TaskProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, UUID> {

    @Modifying
    @Query(nativeQuery = true, value = "select cast(t.id as varchar)        as id,\n" +
            "       t.title                      as title,\n" +
            "       cast(t.lesson_id as varchar) as lessonId,\n" +
            "       t.task_number                as taskNumber,\n" +
            "       t.description                as description,\n" +
            "       t.url                        as url,\n" +
            "       t.priority                   as priority,\n" +
            "       t.must_complete              as mustComplete,\n" +
            "       cast(l.video_id as varchar)  as videoId\n" +
            "from task t\n" +
            "         inner join lesson l on t.lesson_id = l.id\n" +
            "         inner join groups g on l.group_id = g.id\n" +
            "where g.id = :groupId")
    List<TaskProjection> getAllTask(UUID groupId);
}
