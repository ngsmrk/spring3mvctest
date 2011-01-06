package org.ngsmrk.spring.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)

public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public AccountServiceImpl() {
        super();
    }

    @Override
    public Map getBanks() {
        final Map refData = new HashMap();
        refData.put("bankName", "RBS");

        return refData;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackForClassName={"org.ngsmrk.spring.service.AccountCreationException"})
    public void createAccount(Account account) throws AccountCreationException {
        accountDAO.createAccount(account);
    }

    @Override
    public Account getAccount(long id) {
        return accountDAO.getAccount(id);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }

    @Override
    public Collection<Account> getAccounts() {
        return accountDAO.getAccounts();
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void delete(long id) {
        accountDAO.delete(id);
    }
}