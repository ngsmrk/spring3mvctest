package org.ngsmrk.spring.service;

import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void createAccount(Account account) throws AccountCreationException {
        sessionFactory.getCurrentSession().save(account);
    }

    public Account getAccount(long id) {
        return (Account) sessionFactory.getCurrentSession().load(Account.class, id);
    }

    public void updateAccount(Account account) {
        sessionFactory.getCurrentSession().update(account);
    }

    Collection<Account> getAccounts() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Account.class);        
        return criteria.list();
    }

    void delete(long id) {
        sessionFactory.getCurrentSession().delete(getAccount(id));
    }
}