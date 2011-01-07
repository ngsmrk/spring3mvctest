/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngsmrk.spring.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Angus
 */
@Controller
@RequestMapping("/adduser.htm")
@SessionAttributes({"userForm", "pageNum"})
public class AddUserController {

    @Autowired
    private UserValidator validator;

    public AddUserController() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(ModelMap map) throws Exception {

        map.addAttribute("userForm", new User());
        map.addAttribute("pageNum", 1);

        return "userwizard/page1form";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(method = RequestMethod.POST)
    // the order of the parameters is important - in this case the BindingResult is for the user
    public ModelAndView processSubmit(@ModelAttribute("userForm") User theUser,
            BindingResult result, @ModelAttribute(value = "pageNum") int pageNum, @RequestParam(value = "_action") String action, SessionStatus status)
            throws Exception {

        if ("Cancel".equals(action)) {
            return processCancel();
        } else if ("Finish".equals(action)) {
            return processFinish(theUser, result);
        } else if ("Previous".equals(action)) {
            return goBackOnePage(theUser, pageNum);
        } else if ("Next".equals(action)) {
            return processPageSubmission(theUser, result, pageNum);
        } else {
            throw new IllegalArgumentException("Invalid action: " + action);
        }
    }

    private ModelAndView processPageSubmission(User theUser, BindingResult result, int pageNum) {
        validatePage(theUser, result, pageNum);

        if (result.hasErrors()) {
            return getPageView(pageNum, theUser);
        } else {
            return goForwardOnePage(theUser, pageNum);
        }
    }

    private ModelAndView getPageView(int newPageNum, User theUser) {
        final ModelAndView modelAndView = new ModelAndView("userwizard/page" + newPageNum + "form");
        modelAndView.addObject("pageNum", newPageNum);
        modelAndView.addObject("userForm", theUser);

        return modelAndView;
    }

    private ModelAndView processFinish(User user, BindingResult errors) throws Exception {

        validator.validatePage3Form(user, errors);
        if (errors.hasErrors()) {
            return new ModelAndView("userwizard/page3form");
        }

        return new ModelAndView("userwizard/resultform", "user", user);
    }

    private ModelAndView processCancel() throws Exception {
        return new ModelAndView(new RedirectView("/", true));
    }

    private void validatePage(User user, Errors errors, int page) {

        //page is 1-indexed
        switch (page) {
            case 1: //if page 1 , go validate with validatePage1Form
                validator.validatePage1Form(user, errors);
                break;
            case 2: //if page 2 , go validate with validatePage2Form
                validator.validatePage2Form(user, errors);
                break;
        }
    }

    private ModelAndView goBackOnePage(User theUser, int pageNum) {

        int newPageNum = pageNum - 1;
        return getPageView(newPageNum, theUser);
    }

    private ModelAndView goForwardOnePage(User theUser, int pageNum) {

        int newPageNum = pageNum + 1;
        return getPageView(newPageNum, theUser);
    }
}
