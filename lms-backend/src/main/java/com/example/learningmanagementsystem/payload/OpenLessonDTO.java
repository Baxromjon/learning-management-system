package com.example.learningmanagementsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenLessonDTO {
    private String title;
    private boolean active;
    private String description;
    private String speaker;
    private Timestamp startTime;
    private String url;
}
