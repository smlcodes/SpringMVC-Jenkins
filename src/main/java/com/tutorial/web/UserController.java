package com.tutorial.web;

import com.tutorial.service.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping("/getEmployeeDetails")
    public String getUser(ModelMap map, HttpServletRequest request) {

        map.addAttribute("user", userService.getEmployeeDetails());

        return "userdetail";
    }

    @RequestMapping("/getEmployeeDetails1")
    public String getUser() {
        return "userdetail";
    }

    @RequestMapping("/getMapObject")
    public @ResponseBody
    Map getMapObject() {

        Map map = new HashMap();
        map.put("UserName", "Usha");
        map.put("Gender", "Female");
        return map;
    }

    @RequestMapping("/getInfo/{userType}")
    public @ResponseBody
    Map getUser(@PathVariable String userType) {

        Map resultMap = new HashMap();

        if ("employee".equals(userType)) {
            resultMap.put("data", userService.getEmployeeDetails());
        } else if ("clerk".equals(userType)) {
            resultMap.put("data", userService.getClerkDetails());
        } else {
            resultMap.put("data", "No record found");
        }
        return resultMap;
    }

}
