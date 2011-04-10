/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ngsmrk.spring.mockitoexamples;

import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.Mockito;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Angus
 */
public class ArgumentCaptorTest {

    public ArgumentCaptorTest() {
    }

    @Test
    public void testArgumentCaptor() throws Exception {

        UserManager userManager = Mockito.mock(UserManager.class);
        UserService userService = new UserService(userManager);
        final String name = "name";
        userService.save(name);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(userManager, Mockito.times(1)).save(captor.capture());

        assertEquals(name, captor.getValue());
    }
}