package org.ngsmrk.spring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class AccountTest {

    @Test
    public void testEquals() {

        Account account1 = new Account();
        Account account2 = new Account();

        assertEquals(account1, account1);
        assertEquals(account2, account2);
        assertFalse(account1.equals(account2));
    }

    @Test
    public void testEqualsWithInitialisedState() {

        Long id = 1L;
        String accountNumber = "555";
        Account account1 = new Account(id, accountNumber);
        Account account2 = new Account(id, accountNumber);

        assertEquals(account1, account1);
        assertEquals(account2, account2);
        assertEquals(account1, account2);

        account2.setId(2L);
        assertEquals(account1, account1);
        assertEquals(account2, account2);
        assertFalse(account1.equals(account2));
    }

    @Test
    public void testEqualsWithDifferentAccountNumber() {

        Long id = 1L;
        Account account1 = new Account(id, "555");
        Account account2 = new Account(id, "666");

        assertEquals(account1, account1);
        assertEquals(account2, account2);
        assertEquals(account1, account2);
    }

    @Test
    public void testHashCode() {

        Long id = 1L;
        Account account1 = new Account(id, "555");
        Account account2 = new Account(id, "666");

        assertEquals(account1.hashCode(), account1.hashCode());
        assertEquals(account2.hashCode(), account2.hashCode());
        assertEquals(account1.hashCode(), account2.hashCode());
    }
}