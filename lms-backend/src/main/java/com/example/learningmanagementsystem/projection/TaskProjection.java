package com.example.learningmanagementsystem.projection;

import java.util.UUID;

public interface TaskProjection {
    UUID getId();
    String getTitle();
    UUID getLessonId();
    int getTaskNumber();
    String getDescription();
    String getUrl();
    String getPriority();
    boolean getMustComplete();
    UUID getVideoId();
}
