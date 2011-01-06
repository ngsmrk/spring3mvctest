package org.ngsmrk.spring.mvc;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

/**
 * .
 *
 * @author Initial: amark
 * @version 1.0
 */
public class AccountValidator implements Validator {
    
    public boolean supports(Class aClass) {
       return aClass.isAssignableFrom(AccountBean.class);
    }

    public void validate(Object o, Errors errors) {

        AccountBean bean = (AccountBean) o;
        final String accountNumber = bean.getAccountNumber();

        if (accountNumber == null || accountNumber.trim().length() == 0) {
            errors.rejectValue("accountNumber", "account.form.accountNumber.required", "Account number must be supplied");
        }
    }    
}
