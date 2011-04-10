/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ngsmrk.spring.mockitoexamples;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.Mockito;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Angus
 */
public class AnswerTest {

    public AnswerTest() {
    }

    @Test
    public void testThenAnswer() throws Exception {

        UserManager userManager = Mockito.mock(UserManager.class);

        Mockito.when(userManager.getUserCount()).thenAnswer(new Answer(){
            private int count;

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return ++count;
            }
        });

        UserService userService = new UserService(userManager);
        assertEquals(1, userService.getUserCount());
        assertEquals(2, userService.getUserCount());
        assertEquals(3, userService.getUserCount());
    }
	
    @Test
    public void testThenAnswerUsingInvocationArgs() throws Exception {

        UserService userService = Mockito.mock(UserService.class);

        Mockito.when(userService.getUserCount(Mockito.anyString())).thenAnswer(new Answer(){
            private int count;

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();	

				if (args[0].equals("up")) {
					count++;
				}
				
				if (args[0].equals("down")) {
					count--;
				}
				
                return count;
            }
        });

        assertEquals(1, userService.getUserCount("up"));
        assertEquals(0, userService.getUserCount("down"));
        assertEquals(1, userService.getUserCount("up"));
        assertEquals(1, userService.getUserCount("none"));
    }	
}