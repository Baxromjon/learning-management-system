package com.example.learningmanagementsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDTO {
    private String title;
    private int lessonNumber;
    private UUID courseId;
    private String url;
    private UUID videoId;
}
