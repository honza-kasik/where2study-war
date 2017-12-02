package cz.honzakasik.upol.where2study.users;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cz.honzakasik.upol.where2study.users.User;
import cz.honzakasik.upol.where2study.users.UserManager;

@SessionScoped
@Named
public class Login implements Serializable {
	
	@Inject
	private Credentials credentials;
	
	@EJB
	private UserManager userManager; 
	
	private static final long serialVersionUID = 7965455427888195913L;
  
	private User currentUser;

	public void login() throws Exception {
		User user = userManager.findUser(credentials.getEmail(), credentials.getPasswordHash());
		if (user != null) {
			this.currentUser = user;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome, " + currentUser.getFirstname()));
		}
	}
	
	public void logout() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Goodbye, " + currentUser.getFirstname()));
		currentUser = null;
	}
	
	public boolean isLoggedIn() {
		return currentUser != null;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

}
