package com.tutorial;

import com.tutorial.domain.User;
import com.tutorial.web.UserController;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

public class ITStandaloneUserControllerTest {

    @Test
    public void showHomePage() throws Exception {
        MockMvcBuilders.standaloneSetup(new UserController()).build()
                .perform(get("/getEmployeeDetails1"))
                .andExpect(status().isOk())
                .andExpect(view().name("userdetail"));
    }

    @Test
    public void showHomePage1() throws Exception {

        ModelMap map = mock(ModelMap.class);

        User user = new User();
        user.setAgeGroup("24");
        user.setGender("F");
        user.setUserName("Usha");

        when(map.addAttribute("user", user)).thenReturn(map);

        Object s = MockMvcBuilders.standaloneSetup(new UserController()).build()
                .perform(get("/getEmployeeDetails1"))
                .andExpect(status().isOk())
                .andExpect(view().name("userdetail"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(s);
    }
}
