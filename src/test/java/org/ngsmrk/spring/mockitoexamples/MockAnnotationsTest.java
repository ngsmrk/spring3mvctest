package org.ngsmrk.spring.mockitoexamples;

import static junit.framework.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


/**
 * .
 *
 * @author Initial: amark
 * @version 1.0
 */
public class MockAnnotationsTest {

    @Mock
    private Calculator theCalculator;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockInterface() throws Exception {
        assertNotNull(theCalculator);
    }

    @Test
    public void testMockCalculation() throws Exception {

        Mockito.when(theCalculator.calculate(Mockito.anyInt(), Mockito.anyInt())).thenReturn(-1);
        assertEquals(-1, theCalculator.calculate(0, 0));

        // test exact value matching
        Mockito.when(theCalculator.calculate(-1, -1)).thenReturn(0);
        assertEquals(0, theCalculator.calculate(-1, -1));

        // will still use generic match
        assertEquals(-1, theCalculator.calculate(-1, -2));
    }

    @Test(expected = InvalidUseOfMatchersException.class)
    public void testMisuseOfMatchers() throws Exception {

        // test fails because you have to simply matchers for all parameters - cannot mix
        Mockito.when(theCalculator.calculate(-1, Mockito.anyInt())).thenReturn(-1);
        assertEquals(0, theCalculator.calculate(-1, -1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowException() throws Exception {
        Mockito.when(theCalculator.calculate(Mockito.anyInt(), Mockito.anyInt())).thenThrow(new IllegalArgumentException());
        theCalculator.calculate(0, 0);
    }

    @Test
    public void testMultipleValues() throws Exception {

        Mockito.when(theCalculator.calculate(Mockito.anyInt(), Mockito.anyInt())).thenReturn(1, 2, 3);
        assertEquals(1, theCalculator.calculate(0, 0));
        assertEquals(2, theCalculator.calculate(0, 0));
        assertEquals(3, theCalculator.calculate(0, 0));

        // returns the last value once there are no new values
        assertEquals(3, theCalculator.calculate(0, 0));
    }

    @Test
    public void testUseAnswer() throws Exception {

        Mockito.when(theCalculator.calculate(10, 20)).thenAnswer(new Answer<Integer>() {
            public Integer answer(InvocationOnMock
                    invocation) throws Throwable {
                return (Integer) invocation.getArguments()[0];
            }
        });

        assertEquals(10, theCalculator.calculate(10, 20));

    }

    interface Calculator {
        int calculate(int value1, int value2);
    }

}
