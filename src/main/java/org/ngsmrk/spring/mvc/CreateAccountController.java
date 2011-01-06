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
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/createaccount.htm")
public class CreateAccountController {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private AccountService accountService;

    public CreateAccountController() {
        super();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(ModelMap model) {

        Account account = new Account();
        AccountBean accountBean = new AccountBean(account);

        model.addAttribute("account", accountBean);

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
        accountService.createAccount(newAccount);

        return "account-created";
    }

    @ModelAttribute("bankName")
    protected Map referenceData() throws Exception {
        final Map refData = accountService.getBanks();

        logger.info("Returning refdata: " + refData);

        return refData;
    }

}