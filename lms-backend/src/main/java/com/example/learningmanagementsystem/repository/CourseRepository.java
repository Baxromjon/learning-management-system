package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Course;
import com.example.learningmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    List<Course> findByMentorId(User user);
}
