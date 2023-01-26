package com.example.learningmanagementsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phoneNumber;
    private String password;
    private Integer roleId;
    private String gender;
    private String parentKey;
}
