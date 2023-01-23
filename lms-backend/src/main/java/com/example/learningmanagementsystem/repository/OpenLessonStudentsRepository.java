package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.OpenLessonStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OpenLessonStudentsRepository extends JpaRepository<OpenLessonStudents, UUID> {
}
