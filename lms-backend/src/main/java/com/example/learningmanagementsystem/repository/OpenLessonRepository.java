package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.OpenLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OpenLessonRepository extends JpaRepository<OpenLesson, UUID> {
}
