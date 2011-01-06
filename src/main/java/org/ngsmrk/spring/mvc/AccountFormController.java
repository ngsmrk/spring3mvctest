package org.ngsmrk.spring.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ngsmrk.spring.service.Account;
import org.ngsmrk.spring.service.AccountService;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AccountFormController extends SimpleFormController {

    private final Log logger = LogFactory.getLog(getClass());

    private AccountService accountService;

    public AccountFormController() {
        super();
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) {

        long accountID = Long.parseLong(request.getParameter("accountID"));
        Account account = accountService.getAccount(accountID);
        AccountBean accountBean = new AccountBean(account);

        logger.info("Returning account: " + accountBean.getAccountNumber());

        return accountBean;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
                                    HttpServletResponse response, Object command, BindException errors)
            throws Exception {

        AccountBean theAccount = (AccountBean) command;
        logger.info("Processing account: " + theAccount.getAccountNumber());

        Account newAccount = theAccount.getAccount();
        accountService.updateAccount(newAccount);
        return new ModelAndView(this.getSuccessView(), "account", newAccount);
    }

    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        final Map refData = accountService.getBanks();

        logger.info("Returning refdata: " + refData);

        return refData;
    }

}