/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ngsmrk.spring.mvc;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Angus
 */
@Controller
@RequestMapping("/beans.htm")
@SessionAttributes("bean") // use SessionAttributes to maintain state in session
public class BeanController {

    private final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(method=RequestMethod.GET)
    public String setupForm(ModelMap map) {

        Bean bean = new Bean();
        map.addAttribute("bean", bean);

        return "bean/form";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String processSubmit(@ModelAttribute("bean") @Valid Bean bean, BindingResult result) {
       
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        logger.info(validator.getClass());

        logger.info(bean.toString());

        logger.info(result.getAllErrors().toString());

        if (result.hasErrors()) {
            return "bean/form";
        }
        
        return "bean/done";
    }

}
