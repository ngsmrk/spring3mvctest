package org.ngsmrk.spring.service;

import java.util.Map;

public interface AccountService {

    Map getBanks();

    void createAccount(Account account) throws AccountCreationException;

    Account getAccount(long id);

    void updateAccount(Account account);
}