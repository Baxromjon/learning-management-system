package com.example.learningmanagementsystem.payload;

import com.example.learningmanagementsystem.enums.OpenLessonEnumStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenLessonStudentsDTO {
    private String fullName;
    private String phoneNumber;
    private UUID openLessonId;
    private String comment;
}
