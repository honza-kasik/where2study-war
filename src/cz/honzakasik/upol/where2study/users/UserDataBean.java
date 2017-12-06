package cz.honzakasik.upol.where2study.users;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ViewScoped
@ManagedBean
public class UserDataBean {
	
	@Inject
	private Login login;
	
	@EJB
	private UserManager userManager;
	
	private int id;
	
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	
	private String[] preferredBuildings;
	private String[] preferredDepartments;
	private String[] preferredRooms;
	
	public void populateWithLoggedUserData() {
		final User u = userManager.findUser(login.getCurrentUser().getId());
		id = u.getId();
		firstName = u.getFirstName();
		lastName = u.getLastName();
		email = u.getEmail();
	}
	
	/**
	 * Save edited user data
	 */
	@Transactional
	public void save() {
		final User u = userManager.findUser(id);
		if (email != null || !email.isEmpty()) u.setEmail(email);
		u.setFirstName(firstName);
		u.setLastName(lastName);
	}
	
	

}
