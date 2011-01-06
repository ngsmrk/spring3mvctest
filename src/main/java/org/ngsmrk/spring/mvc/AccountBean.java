package org.ngsmrk.spring.mvc;

import org.ngsmrk.spring.service.Account;

import java.io.Serializable;

public class AccountBean implements Serializable {

    private final Account account;

    public AccountBean() {
        this.account = new Account();
    }

    public AccountBean(Account account) {
        this.account = account;
    }

    public String getAccountNumber() {
        return account.getAccountNumber();
    }

    public void setAccountNumber(String accountNumber) {
        account.setAccountNumber(accountNumber);
    }

    public Long getId() {
        return account.getId();
    }

    public Account getAccount() {
        return account;
    }
}