package org.ngsmrk.spring.mvc;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.springframework.validation.BindException;
import org.ngsmrk.spring.service.Account;

/**
 * .
 *
 * @author Initial: amark
 * @version 1.0
 */
public class AccountValidatorTest {
    
    private AccountValidator validator;

    @Before
    public void setUp() {
        validator = new AccountValidator();
    }

    @Test
    public void testNullValue() {

        AccountBean bean = new AccountBean(new Account());
        BindException errors = new BindException(bean, "quote");

        validator.validate(bean, errors);
        assertTrue(errors.hasFieldErrors());

        assertEquals("account.form.accountNumber.required", errors.getFieldError("accountNumber").getCode());
    }

    @Test
    public void testBlankValue() {

        AccountBean bean = new AccountBean(new Account());
        bean.setAccountNumber("  ");
        BindException errors = new BindException(bean, "quote");

        validator.validate(bean, errors);
        assertTrue(errors.hasFieldErrors());

        assertEquals("account.form.accountNumber.required", errors.getFieldError("accountNumber").getCode());
    }

    @Test
    public void testSupportsClass() {

        assertTrue(validator.supports(AccountBean.class));
        assertFalse(validator.supports(Integer.class));
    }
}
