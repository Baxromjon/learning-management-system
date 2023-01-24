package com.example.learningmanagementsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private double amount;
    private UUID userId;
    private UUID payTypeId;
}
