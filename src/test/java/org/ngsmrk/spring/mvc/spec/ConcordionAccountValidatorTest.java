/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ngsmrk.spring.mvc.spec;


import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.ngsmrk.spring.mvc.AccountBean;
import org.ngsmrk.spring.mvc.AccountValidator;
import org.springframework.validation.BindException;
import org.ngsmrk.spring.service.Account;
import org.springframework.validation.FieldError;

@RunWith(ConcordionRunner.class)
public class ConcordionAccountValidatorTest {

    public ConcordionAccountValidatorTest() {
    }

    public String testAccountNumber(String accountNumber) {

        AccountBean bean = new AccountBean(new Account());
        bean.setAccountNumber(accountNumber);
        BindException errors = new BindException(bean, "quote");

        AccountValidator validator = new AccountValidator();
        validator.validate(bean, errors);
        final FieldError fieldError = errors.getFieldError("accountNumber");

        return fieldError != null ? fieldError.getDefaultMessage() : "";
    }

}