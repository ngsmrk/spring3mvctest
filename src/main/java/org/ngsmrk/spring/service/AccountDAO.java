package org.ngsmrk.spring.service;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AccountDAO extends HibernateDaoSupport {

    public void createAccount(Account account) throws AccountCreationException {
        getHibernateTemplate().save(account);
    }

    public Account getAccount(long id) {
        return (Account) getHibernateTemplate().load(Account.class, id);
    }

    public void updateAccount(Account account) {
        getHibernateTemplate().update(account);
    }
}