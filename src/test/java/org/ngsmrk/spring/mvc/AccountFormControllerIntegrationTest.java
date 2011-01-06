package org.ngsmrk.spring.mvc;

import org.hibernate.ObjectNotFoundException;
import org.ngsmrk.spring.service.Account;
import org.ngsmrk.spring.service.AccountService;
import org.ngsmrk.spring.service.AccountServiceImpl;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.AbstractTransactionalSpringContextTests;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public class AccountFormControllerIntegrationTest extends AbstractTransactionalSpringContextTests {

    private AccountFormController accountFormController;

    private AccountService accountService;

    private Account newAccount;

    public void setAccountFormController(AccountFormController controller) {
        this.accountFormController = controller;
    }

    public void setAccountService(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @Override
    public void onSetUp() throws Exception {
        super.onSetUp();

        newAccount = new Account();
        newAccount.setAccountNumber("777");
        accountService.createAccount(newAccount);
    }

    @Override
    public void onTearDown() throws Exception {
        super.onTearDown();
    }

    @Override
    public String[] getConfigLocations() {
        String[] loc = {"classpath:/spring/application-context-ds.xml",
                "classpath:/WEB-INF/spring/application-context-hibernate.xml",
                "classpath:/WEB-INF/spring/application-context-services.xml",
                "classpath:/WEB-INF/spring/springapp-servlet.xml"
        };

        return loc;
    }

/*
    public void testOnSubmit() throws Exception {		

		AccountBean account = new AccountBean();
        ModelAndView modelAndView = accountFormController.onSubmit(null, null, account, null);	
		assertEquals("account-created", modelAndView.getViewName());
    }
	
	public void testFormBackingObject() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("accountID", "1");
		AccountBean account = (AccountBean) accountFormController.formBackingObject(request);
		assertEquals("1", account.getAccountNumber());
	}
*/

    public void testOnSubmitInvalidAccount() throws Exception {

        AccountBean account = new AccountBean(newAccount);
        ModelAndView modelAndView = accountFormController.onSubmit(null, null, account, null);
        assertEquals("account-created", modelAndView.getViewName());
    }

    public void testFormBackingObjectInvalidAccount() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("accountID", "-999");
        try {
            AccountBean account = (AccountBean) accountFormController.formBackingObject(request);
            fail("Invalid account id supplied");
        } catch (ObjectNotFoundException expected) {
        }
    }

    public void testReferenceData() throws Exception {
        Map refData = accountFormController.referenceData(new MockHttpServletRequest());
        assertNotNull(refData);
        assertEquals(1, refData.size());
        assertEquals("RBS", refData.get("bankName"));
    }
}