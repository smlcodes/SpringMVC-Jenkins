package com.tutorial.serviceImpl;

import com.tutorial.domain.User;
import com.tutorial.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    
    @Override
    public User getEmployeeDetails() {
        User user = new User();
        user.setAgeGroup("24");
        user.setGender("F");
        user.setUserName("Usha");
        return user;
    }

    @Override
    public User getClerkDetails() {
        User user = new User();
        user.setAgeGroup("30");
        user.setGender("F");
        user.setUserName("Mala");
        return user;
    }

}
