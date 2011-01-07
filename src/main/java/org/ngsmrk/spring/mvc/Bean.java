/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngsmrk.spring.mvc;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Angus
 */
public class Bean {

    @Override
    public String toString() {
        return "Bean{" + "name=" + name + "child=" + child + '}';
    }

    @NotEmpty
    @Size(min=1, max=10)
    private String name;

    @Valid
    private final Child child = new Child();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Child getChild() {
        return child;
    }
}
