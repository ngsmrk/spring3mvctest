/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngsmrk.spring.mvc;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Angus
 */
public class AddUserControllerTest {

    AddUserController instance;

    public AddUserControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        instance = new AddUserController();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCommandName() {
        assertEquals("userForm", instance.getCommandName());
    }

    @Test
    public void testProcessFinish() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        User user = new User();
        BindException errors = new BindException(user, "user");

        ModelAndView result = instance.processFinish(request, response, user, errors);
        assertEquals("userwizard/resultform", result.getViewName());
        assertEquals(user, result.getModel().get("user"));
    }

    @Test
    public void testProcessCancel() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        User user = new User();
        BindException errors = new BindException(user, "user");

        ModelAndView result = instance.processCancel(request, response, user, errors);
        RedirectView innerView = (RedirectView) result.getView();
        assertEquals("hello.htm", innerView.getUrl());
    }
}
