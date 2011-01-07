/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngsmrk.spring.mvc;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.RedirectView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/application-context-ds.xml",
    "classpath:/WEB-INF/spring/application-context-hibernate.xml",
    "classpath:/WEB-INF/spring/springapp-servlet.xml"
})
public class AddUserControllerTest {

    @Autowired
    AddUserController addUserController;

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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSetupForm() throws Exception {

        ModelMap map = new ModelMap();
        String view = addUserController.setupForm(map);

        assertTrue(map.get("userForm") instanceof User);
        assertEquals(1, map.get("pageNum"));
        assertEquals("userwizard/page1form", view);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAction() throws Exception {

        User user = new User();
        int pageNum = 2;

        addUserController.processSubmit(user, null, pageNum, "FOO", null);
    }

    @Test
    public void testGoFromPage1ToPage2ValidationFails() throws Exception {

        User user = new User();
        BindException errors = new BindException(user, "user");
        int pageNum = 1;

        // stay on page 1 because validation fails
        ModelAndView view = addUserController.processSubmit(user, errors, pageNum, "Next", null);
        assertEquals("userwizard/page1form", view.getViewName());
        assertSame(user, view.getModel().get("userForm"));
        assertEquals(1, view.getModel().get("pageNum"));

        assertEquals(1, errors.getErrorCount());
        assertEquals("required.userName", errors.getFieldError("userName").getCode());
    }

    @Test
    public void testGoFromPage1ToPage2ValidationPasses() throws Exception {

        User user = new User();
        user.setUserName("TEST");
        BindException errors = new BindException(user, "user");
        int pageNum = 1;

        // move to page 2 because validation passes
        ModelAndView view = addUserController.processSubmit(user, errors, pageNum, "Next", null);
        assertEquals("userwizard/page2form", view.getViewName());
        assertSame(user, view.getModel().get("userForm"));
        assertEquals(2, view.getModel().get("pageNum"));

        assertEquals(0, errors.getErrorCount());
    }

    @Test
    public void testGoFromPage2ToPage3ValidationFails() throws Exception {

        User user = new User();
        BindException errors = new BindException(user, "user");
        int pageNum = 2;

        // stay on page 2 because validation fails
        ModelAndView view = addUserController.processSubmit(user, errors, pageNum, "Next", null);
        assertEquals("userwizard/page2form", view.getViewName());
        assertSame(user, view.getModel().get("userForm"));
        assertEquals(2, view.getModel().get("pageNum"));

        assertEquals(1, errors.getErrorCount());
        assertEquals("required.password", errors.getFieldError("password").getCode());
    }

    @Test
    public void testGoFromPage2ToPage3ValidationPasses() throws Exception {

        User user = new User();
        user.setPassword("TEST");
        BindException errors = new BindException(user, "user");
        int pageNum = 2;

        // move to page 3 because validation passes
        ModelAndView view = addUserController.processSubmit(user, errors, pageNum, "Next", null);
        assertEquals("userwizard/page3form", view.getViewName());
        assertSame(user, view.getModel().get("userForm"));
        assertEquals(3, view.getModel().get("pageNum"));

        assertEquals(0, errors.getErrorCount());
    }

    @Test
    public void testGoBackFromPage2ToPage1() throws Exception {

        User user = new User();
        int pageNum = 2;

        ModelAndView view = addUserController.processSubmit(user, null, pageNum, "Previous", null);
        assertEquals("userwizard/page1form", view.getViewName());
        assertSame(user, view.getModel().get("userForm"));
        assertEquals(1, view.getModel().get("pageNum"));
    }

    @Test
    public void testGoBackFromPage3ToPage2() throws Exception {

        User user = new User();
        int pageNum = 3;

        ModelAndView view = addUserController.processSubmit(user, null, pageNum, "Previous", null);
        assertEquals("userwizard/page2form", view.getViewName());
        assertSame(user, view.getModel().get("userForm"));
        assertEquals(2, view.getModel().get("pageNum"));
    }

    @Test
    public void testCancellation() throws Exception {

        User user = new User();

        ModelAndView view = addUserController.processSubmit(user, null, 1, "Cancel", null);
        assertTrue("View is not redirect view", view.getView() instanceof RedirectView);
        assertEquals("Redirect url is not correct.", "/", ((AbstractUrlBasedView) view.getView()).getUrl());
    }

    @Test
    public void testFinishValidation() throws Exception {

        User user = new User();
        BindException errors = new BindException(user, "user");

        ModelAndView view = addUserController.processSubmit(user, errors, 1, "Finish", null);
        assertEquals("userwizard/page3form", view.getViewName());
        assertEquals(1, errors.getErrorCount());
        assertEquals("required.remark", errors.getFieldError("remark").getCode());
    }

    @Test
    public void testFinish() throws Exception {

        User user = new User();
        user.setRemark("test");
        BindException errors = new BindException(user, "user");

        ModelAndView view = addUserController.processSubmit(user, errors, 1, "Finish", null);
        assertEquals(0, errors.getErrorCount());
        assertEquals("userwizard/resultform", view.getViewName());
        assertSame(user, view.getModel().get("user"));
    }
}
