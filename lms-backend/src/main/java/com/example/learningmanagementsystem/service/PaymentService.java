package com.example.learningmanagementsystem.service;


import com.example.learningmanagementsystem.entity.Cash;
import com.example.learningmanagementsystem.entity.PayType;
import com.example.learningmanagementsystem.entity.Payment;
import com.example.learningmanagementsystem.entity.User;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.PaymentDTO;
import com.example.learningmanagementsystem.repository.CashRepository;
import com.example.learningmanagementsystem.repository.PayTypeRepository;
import com.example.learningmanagementsystem.repository.PaymentRepository;
import com.example.learningmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PayTypeRepository payTypeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CashRepository cashRepository;

    public ApiResult add(PaymentDTO paymentDTO) {
        try{
            User user = userRepository.findById(paymentDTO.getUserId()).orElseThrow();
            PayType payType = payTypeRepository.findById(paymentDTO.getPayTypeId()).orElseThrow();
            Payment payment=new Payment(
                    paymentDTO.getAmount(),
                    user,
                    new Date(),
                    payType
            );
            paymentRepository.save(payment);
            Cash cash = cashRepository.findByUser(user);
            cash.setAmount(cash.getAmount()+payment.getAmount());
            cashRepository.save(cash);
            return new ApiResult(true, "Successfully add payment");
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResult(false, "Error in add payment");
        }
    }
}
