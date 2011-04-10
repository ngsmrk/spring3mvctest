package org.ngsmrk.spring.mockitoexamples;

import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * .
 *
 * @author Initial: amark
 * @version 1.0
 */
public class VerificationTest {

    @Before
    public void setUp() {

    }

    @Test
	public void testInvocationOrder() {
		fail("Not implemented");
	}
	
    @Test
	public void testMethodNotInvoked() {
	
		UserManager userManager = Mockito.mock(UserManager.class);
		UserService userService = new UserService(userManager);
        final String name = "name";
        userService.save(name);
		
		Mockito.verify(userManager, Mockito.never()).getUserCount();
	}

    @Test
	public void testNoMoreMethodsInvoked() {
		fail("Not implemented");
	}		
	
    @Test
    public void testVerify() throws Exception {

        UserManager userManager = Mockito.mock(UserManager.class);
        UserService userService = new UserService(userManager);
        final String name = "name";
        userService.save(name);
        
        Mockito.verify(userManager, Mockito.times(1)).save(name);
    }	
}