/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ngsmrk.spring.mvc;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Angus
 */
public class Child {

    @Override
    public String toString() {
        return "Child{" + "name=" + name + '}';
    }

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
