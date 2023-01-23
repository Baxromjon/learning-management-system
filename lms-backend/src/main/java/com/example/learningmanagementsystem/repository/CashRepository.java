package com.example.learningmanagementsystem.repository;

import com.example.learningmanagementsystem.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CashRepository extends JpaRepository<Cash, UUID> {
}
