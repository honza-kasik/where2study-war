package cz.honzakasik.upol.where2study.users;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ViewScoped
@ManagedBean
@Transactional
public class EditUser {
		
	private User user = new User();
	
	@Inject
	private Login login;
	
	@Inject
	private Credentials credentials;
	
	@EJB
	private UserManager userManager;
	
	public EditUser() {
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Register user stored in user field
	 * @return
	 */
	public String register() {
		user.setPasswordHash(UserUtils.getPasswdHash(credentials.getPassword()));
		userManager.createUser(user);
		return "index";
	}
	
	/**
	 * Edit user stored in login
	 * @throws Exception
	 */
	public void editUser() throws Exception {
		userManager.saveUser(user);
	}
	
	public User getPopulatedUser(User currentUser) throws Exception {
		String email = currentUser.getEmail();
		String passwordHash = currentUser.getPasswordHash();
		this.user = userManager.findUser(email, passwordHash);
		return user;
	}

}
