/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngsmrk.spring.mockitoexamples;

/**
 *
 * @author Angus
 */
public class UserService {

    private final UserManager userManager;

    public UserService(UserManager userManager) {
        this.userManager = userManager;
    }

    public int getUserCount() {
        try {
            return userManager.getUserCount();
        } catch (Exception e) {
            return -1;
        }
    }
 
	public int getUserCount(String action) {
		return 0;
	}
	
    public void save(String name) {
        userManager.save(name);
    }
}
