/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngsmrk.spring.mvc;

import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.Validation;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author Angus
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/application-context-ds.xml",
    "classpath:/WEB-INF/spring/application-context-hibernate.xml",
    "classpath:/WEB-INF/spring/springapp-servlet.xml"
})
public class BeanTest {

    private static Validator validator;

    public BeanTest() {
    }

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidationWithNulls() {

        Bean bean = new Bean();
        Set<ConstraintViolation<Bean>> constraintViolations = validator.validate(bean);
        assertEquals(2, constraintViolations.size());

        final Iterator<ConstraintViolation<Bean>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            final ConstraintViolation<Bean> constraintViolation = iterator.next();
            assertEquals("may not be empty", constraintViolation.getMessage());
        }
    }

    @Test
    public void testValidationWithBlanks() {

        Bean bean = new Bean();
        bean.setName("");
        bean.getChild().setName("");

        Set<ConstraintViolation<Bean>> constraintViolations = validator.validate(bean);
        assertEquals(3, constraintViolations.size());
    }

    @Test
    public void testValidationPasses() {
        Bean bean = new Bean();
        
        bean.setName("1");
        bean.getChild().setName("2");
        Set<ConstraintViolation<Bean>> constraintViolations = validator.validate(bean);
        assertEquals(0, constraintViolations.size());
    }
}
