package org.ngsmrk.spring.mvc;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.ngsmrk.spring.service.Account;
import org.ngsmrk.spring.service.AccountService;
import org.springframework.mock.web.MockHttpServletRequest;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import org.springframework.web.servlet.ModelAndView;

public class AccountFormControllerTest {

    private static AccountFormController controller;

    @BeforeClass
    public static void beforeTests() {
        controller = new AccountFormController();
        controller.setSuccessView("success");

        // instantiate mock Accounts and mock AccountService
        Account acc = mock(Account.class);
        when(acc.getId()).thenReturn(111L);
        when(acc.getAccountNumber()).thenReturn("00112233");

        AccountService service = mock(AccountService.class);
        when(service.getAccount(111)).thenReturn(acc);
        controller.setAccountService(service);
    }

    @Before
    public void beforeTest() {
    }

    @Test
    public void testOnSubmit() throws Exception {

        AccountBean account = new AccountBean(new Account());
        ModelAndView modelAndView = controller.onSubmit(null, null, account, null);
        assertViewName(modelAndView, "success");

        // TODO check model attributes
    }

    @Test
    public void testFormBackingObject() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("accountID", "111");
        AccountBean account = (AccountBean) controller.formBackingObject(request);
        assertEquals(new Long(111), account.getId());
    }
}