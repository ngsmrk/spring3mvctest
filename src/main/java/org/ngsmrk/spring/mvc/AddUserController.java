/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngsmrk.spring.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Angus
 */
public class AddUserController extends AbstractWizardFormController {

    public AddUserController() {
        setCommandName("userForm");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception {

        return new User();
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {

        User user = (User) o;

        return new ModelAndView("userwizard/resultform", "user", user);
    }

    @Override
    protected ModelAndView processCancel(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, BindException be) throws Exception {
        return new ModelAndView(new RedirectView("hello.htm"));
    }

    @Override
    protected void validatePage(Object command, Errors errors, int page) {

        UserValidator validator = (UserValidator) getValidator();

        //page is 0-indexed
        switch (page) {
            case 0: //if page 1 , go validate with validatePage1Form
                validator.validatePage1Form(command, errors);
                break;
            case 1: //if page 2 , go validate with validatePage2Form
                validator.validatePage2Form(command, errors);
                break;
            case 2: //if page 3 , go validate with validatePage3Form
                validator.validatePage3Form(command, errors);
                break;
        }
    }
}
