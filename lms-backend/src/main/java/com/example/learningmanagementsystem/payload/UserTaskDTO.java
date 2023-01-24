package com.example.learningmanagementsystem.payload;

import com.example.learningmanagementsystem.enums.TaskStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTaskDTO {
    private boolean looked;
    private int percent;
    private TaskStatusEnum taskStatusEnum;
    private String text;
    private UUID attachmentId;
    private UUID taskId;
}
