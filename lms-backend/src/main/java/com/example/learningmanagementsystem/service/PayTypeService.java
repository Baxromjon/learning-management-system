package com.example.learningmanagementsystem.service;


import com.example.learningmanagementsystem.entity.PayType;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.PayTypeDTO;
import com.example.learningmanagementsystem.repository.PayTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayTypeService {

    @Autowired
    PayTypeRepository payTypeRepository;

    public ApiResult add(PayTypeDTO payTypeDTO) {
        try {
            PayType payType = new PayType(
                    payTypeDTO.getName(),
                    payTypeDTO.getDescription()
            );
            payTypeRepository.save(payType);
            return new ApiResult(true, "Successfully added paytype");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "Error in add paytype");
        }
    }
}
