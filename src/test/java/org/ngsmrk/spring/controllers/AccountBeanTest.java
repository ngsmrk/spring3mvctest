package org.ngsmrk.spring.controllers;

import static org.junit.Assert.*;
import org.junit.Test;
import org.ngsmrk.spring.service.Account;
import org.ngsmrk.spring.mvc.AccountBean;

public class AccountBeanTest {

    @Test
    public void getNewAccount() {
        AccountBean accountBean = new AccountBean(null);
        Account account = accountBean.getAccount();
        assertNull(account);
    }

    @Test
    public void getModifiedAccount() {
        Account existingAccount = new Account(1L, "111");
        AccountBean accountBean = new AccountBean(existingAccount);

        String newAccountNumber = "555";
        accountBean.setAccountNumber(newAccountNumber);

        Account modifiedAccount = accountBean.getAccount();
        assertSame(existingAccount, modifiedAccount);
        assertEquals(existingAccount.getId(), modifiedAccount.getId());
        assertEquals(newAccountNumber, modifiedAccount.getAccountNumber());
    }

}