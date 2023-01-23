package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.entity.Gender;
import com.example.learningmanagementsystem.entity.Role;
import com.example.learningmanagementsystem.entity.User;
import com.example.learningmanagementsystem.enums.GenderEnums;
import com.example.learningmanagementsystem.enums.RoleEnum;
import com.example.learningmanagementsystem.payload.ApiResult;
import com.example.learningmanagementsystem.payload.RegisterDTO;
import com.example.learningmanagementsystem.repository.GenderRepository;
import com.example.learningmanagementsystem.repository.RoleRepository;
import com.example.learningmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    GenderRepository genderRepository;


    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException(phoneNumber));
        return user;
    }

    public UserDetails loadUserByUserId(String userIdFromToken) {
        return userRepository.findById(UUID.fromString(userIdFromToken)).orElseThrow();
    }


    public ApiResult register(RegisterDTO registerDTO) {
        try {
            Gender gender = genderRepository.findById(registerDTO.getGenderId()).orElseThrow();
            Role role = roleRepository.findById(registerDTO.getRoleId()).orElseThrow();
            Set<Role> byName = roleRepository.findByRoleEnum(role.getRoleEnum());
            User user = new User();
            user.setFirstName(registerDTO.getFirstName());
            user.setLastName(registerDTO.getLastName());
            user.setBirthDate(registerDTO.getBirthDate());
            user.setPhoneNumber(registerDTO.getPhoneNumber());
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            user.setGender(gender);
            user.setRoles(byName);
            if (role.getRoleEnum().name().equals(RoleEnum.ROLE_PARENT.toString())) {
                user.setParentKey(randomParentKey());
            }
            if (role.getRoleEnum().name().equals(RoleEnum.ROLE_USER.toString())){
                User parent = userRepository.findUserByParentKey(registerDTO.getParentKey());
                user.setParentId(parent);
            }
            userRepository.save(user);
            return new ApiResult(user.getParentKey(),true, "Successfully registered");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "Error in register");
        }
    }

    public String randomParentKey() {
        int max = 9999999;
        int min = 1000000;
        int floor = (int) Math.floor(Math.random() * (max - min + 1) + min);
        Random rnd = new Random();
        final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder alphabat = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            alphabat.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        alphabat.append(floor);
        return alphabat.toString();
    }
}
