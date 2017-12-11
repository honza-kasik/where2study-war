package cz.honzakasik.upol.where2study.users;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import cz.honzakasik.upol.where2study.users.User;
import cz.honzakasik.upol.where2study.users.UserManager;

/**
 * Controller for login related tasks
 */
@SessionScoped
@ManagedBean
public class Login implements Serializable {
	
	@Inject
	private Credentials credentials;
	
	@EJB
	private UserManager userManager; 
	
	private static final long serialVersionUID = 7965455427888195913L;
  
	private User currentUser;

	/**
	 * Login user
	 * @throws Exception
	 */
	public String login() throws Exception {
		String passwordHash = UserUtils.getPasswdHash(credentials.getPassword());
		User user = userManager.findUser(credentials.getEmail(), passwordHash);
		if (user != null) {
			this.currentUser = user;
		}
		return "index?faces-redirect=true";
	}
	
	/**
	 * Log out user
	 */
	public String logout() {
		currentUser = null;
		return "index?faces-redirect=true";
	}
	
	/**
	 * Return true if any user is logged in
	 * @return
	 */
	public boolean isLoggedIn() {
		return currentUser != null;
	}
	
	/**
	 * Get currently logged in user
	 * @return
	 */
	public User getCurrentUser() {
		return currentUser;
	}

}
