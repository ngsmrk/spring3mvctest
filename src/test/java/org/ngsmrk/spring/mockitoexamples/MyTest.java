package org.ngsmrk.spring.mockitoexamples;

import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * .
 *
 * @author Initial: amark
 * @version 1.0
 */
public class MyTest {

    private LegacyHelper helper;

    @Before
    public void setUp() {

        helper = Mockito.spy(new LegacyHelper());

        Mockito.when(helper.callUrl()).thenReturn(0);
        Mockito.when(helper.callUrl(Mockito.anyLong())).thenReturn("test");

        Mockito.when(helper.callUrl(Mockito.any(Param.class))).thenReturn("bar");
    }

    @Test
    public void testCall() {

        assertEquals(0, helper.callUrl());
        
        assertEquals("test", helper.callUrl(-1));
        assertEquals("test", helper.callUrl(1));

        assertEquals("bar", helper.callUrl(new Param("1")));

        Param mockPolicy = Mockito.mock(Param.class);
        assertEquals("bar", helper.callUrl(mockPolicy));
    }

    class LegacyHelper {


        public int callUrl() {
            return -1;
        }

        public String callUrl(long l) {
            return "foo";
        }

        public String callUrl(Param param) {
            return param != null ? "param: " + param.getValue() : null;
        }
    }    
}
