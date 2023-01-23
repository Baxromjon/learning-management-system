package com.example.learningmanagementsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private String title;
    private int taskNumber;
    private UUID lessonId;
    private String url;
    private String priority;
    private boolean mustComplete;
    private String description;
}
