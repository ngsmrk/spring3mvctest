package org.ngsmrk.spring.service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String accountNumber;

    public Account() {
    }

    public Account(Long id, String accountNumber) {
        this.id = id;
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Account)) return false;

        Account otherAccount = (Account) o;
        Long otherId = otherAccount.getId();
        if (otherId != null && this.id != null) {
            return otherId.equals(this.id);
        } else {
            return super.equals(o);
        }
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : super.hashCode();
    }
}