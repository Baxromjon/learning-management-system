package com.example.learningmanagementsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayTypeDTO {
    private String name;
    private String description;
}
