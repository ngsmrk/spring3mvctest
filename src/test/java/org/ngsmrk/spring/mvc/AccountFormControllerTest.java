package org.ngsmrk.spring.mvc;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.ngsmrk.spring.service.Account;
import org.ngsmrk.spring.service.AccountService;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;

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
    public void testSaveAccount() throws Exception {

        AccountBean account = new AccountBean(new Account());
        account.setAccountNumber("1111");
        BindException errors = new BindException(account, "account");
        String viewName = controller.saveAccount(account, errors, null);
        assertEquals("success", viewName);
    }

    @Test
    public void testAccountValidation() throws Exception {

        AccountBean account = new AccountBean(new Account());
        BindException errors = new BindException(account, "account");
        String viewName = controller.saveAccount(account, errors, null);
        assertEquals("account", viewName);
    }

    @Test
    public void testFormBackingObject() throws Exception {

        Long accountID = new Long(111);
        ModelMap model = new ModelMap();
        String viewName = controller.setupForm(accountID, model);
        assertEquals("account", viewName);

        AccountBean account = (AccountBean) model.get("account");
        assertEquals(accountID, account.getId());
    }
}