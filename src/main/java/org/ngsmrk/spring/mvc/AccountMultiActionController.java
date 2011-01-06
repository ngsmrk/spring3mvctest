/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ngsmrk.spring.mvc;

import org.ngsmrk.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Angus
 */
@Controller
public class AccountMultiActionController {

    @Autowired
    private AccountService accountService;

    /**
     * Custom handler for displaying accounts.
     * Note that this handler returns a plain {@link ModelMap} object instead of
     * a ModelAndView, thus leveraging convention-based model attribute names.
     * It relies on the RequestToViewNameTranslator to determine the logical
     * view name based on the request URL: "/accounts.htm" -> "accounts".
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/accounts.htm")
    public ModelMap listAccounts() {
        return new ModelMap("accounts", accountService.getAccounts());
    }

    /**
     * Custom handler for deleting an account.
     * Note that this handler returns a plain {@link ModelMap} object instead of
     * a ModelAndView, thus leveraging convention-based model attribute names.
     * It relies on the RequestToViewNameTranslator to determine the logical
     * view name based on the request URL: "/deleteAccount.htm" -> "deleteAccount".
     * @param accountID the ID of the owner to delete
     * @return a String
     */
    @RequestMapping("/deleteaccount.htm")
    public String deleteAccount(@RequestParam("accountID") long accountID) {
        accountService.delete(accountID);

        return "redirect:/accounts.htm";
    }
}
