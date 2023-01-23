package com.example.learningmanagementsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private String title;
    private String description;
    private Timestamp time;
    private String inviteLink;
    private UUID courseId;
    private List<UUID> userId;
}
