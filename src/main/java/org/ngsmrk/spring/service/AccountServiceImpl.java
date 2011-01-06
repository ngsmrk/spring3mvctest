package org.ngsmrk.spring.service;

import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService {

    private AccountDAO accountDAO;

    public AccountServiceImpl() {
        super();
    }

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Map getBanks() {
        final Map refData = new HashMap();
        refData.put("bankName", "RBS");

        return refData;
    }

    public void createAccount(Account account) throws AccountCreationException {
        accountDAO.createAccount(account);
    }

    public Account getAccount(long id) {
        return accountDAO.getAccount(id);
    }

    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }
}