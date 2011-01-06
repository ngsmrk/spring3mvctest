package org.ngsmrk.spring.mvc;

import org.ngsmrk.spring.service.Account;
import org.ngsmrk.spring.service.AccountService;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Map;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
@ContextConfiguration(locations={"classpath:/spring/application-context-ds.xml",
                "classpath:/WEB-INF/spring/application-context-hibernate.xml",
                "classpath:/WEB-INF/spring/springapp-servlet.xml"
        })
public class AccountFormControllerIntegrationTest  {

    @Autowired
    private AccountFormController accountFormController;

    @Autowired
    private AccountService accountService;

    private Account newAccount;

    @Before
    public void onSetUp() throws Exception {

        newAccount = new Account();
        newAccount.setAccountNumber("777");
        accountService.createAccount(newAccount);
    }

    @BeforeTransaction
    public void verifyInitialDatabaseState() {
        // logic to verify the initial state before a transaction is started
    }

    @AfterTransaction
    public void verifyFinalDatabaseState() {
        // logic to verify the final state after transaction has rolled back
    }
    
    @Test
    public void testOnSubmit() throws Exception {

        AccountBean account = new AccountBean(newAccount);
        BindException errors = new BindException(account, "account");

        String viewName = accountFormController.saveAccount(account, errors, null);
        assertEquals("account-created", viewName);
    }

    @Test
    @ExpectedException(ObjectNotFoundException.class)
    public void testFormBackingObjectInvalidAccount() throws Exception {
        accountFormController.setupForm(-999, new ModelMap());
    }

    @Test
    @NotTransactional
    public void testReferenceData() throws Exception {
        Map refData = accountFormController.referenceData(new MockHttpServletRequest());
        assertNotNull(refData);
        assertEquals(1, refData.size());
        assertEquals("RBS", refData.get("bankName"));
    }
}