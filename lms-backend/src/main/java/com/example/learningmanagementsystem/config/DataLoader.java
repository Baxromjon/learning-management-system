package com.example.learningmanagementsystem.config;

import com.example.learningmanagementsystem.entity.Cash;
import com.example.learningmanagementsystem.entity.Role;
import com.example.learningmanagementsystem.entity.User;
import com.example.learningmanagementsystem.enums.RoleEnum;
import com.example.learningmanagementsystem.repository.CashRepository;
import com.example.learningmanagementsystem.repository.RoleRepository;
import com.example.learningmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CashRepository cashRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.findAll().size() == 0) {
            Role student = new Role(RoleEnum.ROLE_USER);
            Role parents = new Role(RoleEnum.ROLE_PARENT);
            Role mentor = new Role(RoleEnum.ROLE_MENTOR);
            Role adminRole = new Role(RoleEnum.ROLE_ADMIN);
            Role superAdmin = new Role(RoleEnum.ROLE_SUPER_ADMIN);
            List<Role> roles = new ArrayList<>();
            roles.add(student);
            roles.add(parents);
            roles.add(mentor);
            roles.add(adminRole);
            roles.add(superAdmin);
            roleRepository.saveAll(roles);
        }

        if (userRepository.findAll().size() == 0) {
            User admin = new User();
//            Cash cash=new Cash();
            admin.setFirstName("Admin");
            admin.setLastName("Adminov");
            admin.setPhoneNumber("+998971234567");
            admin.setRoles(roleRepository.findByRoleEnum(RoleEnum.ROLE_ADMIN));
            admin.setPassword(passwordEncoder.encode("root123"));
            userRepository.save(admin);
            cashRepository.save(new Cash(0,admin));
            }
        }


//        if (genderRepository.findAll().size()==0){
//            Gender male=new Gender(GenderEnums.MALE);
//            Gender female=new Gender(GenderEnums.FEMALE);
//            List<Gender> genders=new ArrayList<>();
//            genders.add(male);
//            genders.add(female);
//            genderRepository.saveAll(genders);
//        }
    }
//}
