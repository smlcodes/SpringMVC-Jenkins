/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.web;

import com.tutorial.domain.User;
import com.tutorial.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-servlet.xml"})
public class UserControllerTest {

    private UserService userServiceMock;

    private UserController userController;

    public UserControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        userController = new UserController();

        userServiceMock = mock(UserService.class);
        ReflectionTestUtils.setField(userController, "userService", userServiceMock);
    }

    @After
    public void tearDown() {

    }

    /**
     * Test of getUser method, of class UserController.
     */
    @Test
    public void testGetInvalidUser() throws Exception {
        String userType = "";
        Map expResult = new HashMap();
        expResult.put("data", "No record found");
        Map result = userController.getUser(userType);
        verifyZeroInteractions(userServiceMock);
        assertEquals(result, expResult);
    }

    @Test
    public void testGetEmployeeUser() throws Exception {
        //http://docs.mockito.googlecode.com/hg/org/mockito/Mockito.html#3
        String userType = "employee";
        Map expResult = new HashMap();

        User user = new User();
        user.setAgeGroup("24");
        user.setGender("F");
        user.setUserName("Usha");
        expResult.put("data", user);

        when(userServiceMock.getEmployeeDetails()).thenReturn(user);
        Map result = userController.getUser(userType);

        verify(userServiceMock, times(1)).getEmployeeDetails();
        verifyNoMoreInteractions(userServiceMock);

        assertEquals(result, expResult);
    }

}
