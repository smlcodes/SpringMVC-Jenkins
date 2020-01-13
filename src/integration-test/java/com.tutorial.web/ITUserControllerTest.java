/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial;

import com.tutorial.domain.User;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:test-servlet.xml"})
@TestExecutionListeners(inheritListeners = false, listeners
        = {DependencyInjectionTestExecutionListener.class,
            DirtiesContextTestExecutionListener.class})
public class ITUserControllerTest {

    public ITUserControllerTest() {
    }

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getEmployeeDetails() throws Exception {

        User user = new User();
        user.setAgeGroup("24");
        user.setGender("F");
        user.setUserName("Usha");
        mockMvc.perform(get("/getEmployeeDetails"))
                .andExpect(status().isOk())
                .andExpect(view().name("userdetail"));

    }

    @Test
    public void getEmployeeUserByType() throws Exception {

        User user = new User();
        user.setAgeGroup("24");
        user.setGender("F");
        user.setUserName("Usha");

        Map m = new HashMap();
        m.put("userName", "Usha");
        m.put("gender", "F");
        m.put("ageGroup", "24");

        Map resultMap = new HashMap();
        resultMap.put("data", user);

        System.out.println(IntegrationTestUtil.convertObjectToString(resultMap));

        ResultActions s = mockMvc.perform(get("/getInfo/{userType}", "employee"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.data", is(m)))
                .andExpect(content().string(IntegrationTestUtil.convertObjectToString(resultMap)))
                .andExpect(jsonPath("$.data.ageGroup", is("24")))
                .andExpect(jsonPath("$.data.gender", is("F")))
                .andExpect(jsonPath("$.data.userName", is("Usha")));

    }

}
