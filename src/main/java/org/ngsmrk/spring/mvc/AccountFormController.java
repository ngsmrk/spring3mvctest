package org.ngsmrk.spring.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ngsmrk.spring.service.Account;
import org.ngsmrk.spring.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/updateaccount.htm")
@SessionAttributes("account") // use SessionAttributes to maintain original state once loaded from db
public class AccountFormController {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private AccountService accountService;

    private String successView = "account-created";

    public AccountFormController() {
        super();
    }

    @RequestMapping(method=RequestMethod.GET)
    public String setupForm(@RequestParam("accountID") long accountID, ModelMap model) {

        Account account = accountService.getAccount(accountID);
        AccountBean accountBean = new AccountBean(account);

        model.addAttribute("account", accountBean);

        logger.info("Returning account: " + accountBean.getAccountNumber());

        return "account";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveAccount(@ModelAttribute("account") AccountBean theAccount,
            BindingResult result, SessionStatus status)
            throws Exception {

        logger.info("Processing account: " + theAccount.getAccountNumber());

        AccountValidator validator = new AccountValidator();
        validator.validate(theAccount, result);
        if (result.hasErrors()) {
            return "account";
        }

        Account newAccount = theAccount.getAccount();
        accountService.updateAccount(newAccount);
        
        return successView;
    }

    @ModelAttribute("bankName")
    public Map referenceData(HttpServletRequest request) throws Exception {
        final Map refData = accountService.getBanks();

        logger.info("Returning refdata: " + refData);

        return refData;
    }

    // For testing purposes
    void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    void setSuccessView(String successView) {
        this.successView = successView;
    }

}