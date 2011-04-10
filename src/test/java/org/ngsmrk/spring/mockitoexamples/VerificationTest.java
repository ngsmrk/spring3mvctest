package org.ngsmrk.spring.mockitoexamples;

import java.util.List;

import static junit.framework.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.InOrder;

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
	
		List firstMock = Mockito.mock(List.class);
		List secondMock = Mockito.mock(List.class);
 
		//using mocks
		firstMock.add("was called first");
		secondMock.add("was called second");
 
		//create inOrder object passing any mocks that need to be verified in order
		InOrder inOrder = Mockito.inOrder(firstMock, secondMock);
 
		//following will make sure that firstMock was called before secondMock
		inOrder.verify(firstMock).add("was called first");
		inOrder.verify(secondMock).add("was called second");	
	}
	
    @Test
	public void testMethodsNeverInvoked() {
	
		UserManager userManager = Mockito.mock(UserManager.class);
		UserService userService = new UserService(userManager);
		
		// this method does not call the UserManager so there should be no calls to the mock		
        userService.getUserCount("name");
		
		// verify method was not called
		Mockito.verifyZeroInteractions(userManager);
	}	
	
    @Test
	public void testNoMoreMethodsInvoked() {
	
        UserManager userManager = Mockito.mock(UserManager.class);
        UserService userService = new UserService(userManager);
        final String name = "name";
        userService.save(name);
        
        Mockito.verify(userManager, Mockito.times(1)).save(name);
		
		// this method does not call the UserManager so there should be no more calls to the mock
		userService.getUserCount("test");
		Mockito.verifyNoMoreInteractions(userManager);
	}		

    @Test
	public void testStubbingConsecutiveCalls() {
	
		UserManager userManager = Mockito.mock(UserManager.class);
		
		// supply 2 values
		Mockito.when(userManager.getUserCount()).thenReturn(1).thenReturn(2);
		
		assertEquals(1, userManager.getUserCount());
		assertEquals(2, userManager.getUserCount());
	}
	
    @Test
	public void testMethodNotInvoked() {
	
		UserManager userManager = Mockito.mock(UserManager.class);
		UserService userService = new UserService(userManager);
        final String name = "name";
        userService.save(name);
		
		// verify method was not called
		Mockito.verify(userManager, Mockito.never()).getUserCount();
	}	
	
    @Test
    public void testVerify() throws Exception {

        UserManager userManager = Mockito.mock(UserManager.class);
        UserService userService = new UserService(userManager);
        final String name = "name";
        userService.save(name);
        
        Mockito.verify(userManager, Mockito.times(1)).save(name);
    }	
	
	@Test
	public void testCallRealMethod() throws Exception {
	
        UserService userService = Mockito.mock(UserService.class);
		
		Mockito.when(userService.getUserCount(Mockito.anyString())).thenCallRealMethod();
		assertEquals(0, userService.getUserCount("boo"));
		
		// check called
        Mockito.verify(userService, Mockito.atLeastOnce()).getUserCount(Mockito.anyString());		
	}
}