package cz.honzakasik.upol.where2study.users;

import java.util.Arrays;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ViewScoped
@ManagedBean
public class UserDataBean {
	
	private static final Logger log = LoggerFactory.getLogger(UserDataBean.class);
	
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
	
	/**
	 * Create new user with data
	 */
	public void register() {
		final User u = new User();
		u.setEmail(email);
		log.info("Password: {}", password);
		u.setPasswordHash(UserUtils.getPasswdHash(password));
		u.setFirstName(firstName);
		u.setLastName(lastName);
		userManager.createUser(u);
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getPreferredBuildings() {
		return preferredBuildings;
	}

	public void setPreferredBuildings(String[] preferredBuildings) {
		this.preferredBuildings = preferredBuildings;
	}

	public String[] getPreferredDepartments() {
		return preferredDepartments;
	}

	public void setPreferredDepartments(String[] preferredDepartments) {
		this.preferredDepartments = preferredDepartments;
	}

	public String[] getPreferredRooms() {
		return preferredRooms;
	}

	public void setPreferredRooms(String[] preferredRooms) {
		this.preferredRooms = preferredRooms;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + Arrays.hashCode(preferredBuildings);
		result = prime * result + Arrays.hashCode(preferredDepartments);
		result = prime * result + Arrays.hashCode(preferredRooms);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDataBean other = (UserDataBean) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (!Arrays.equals(preferredBuildings, other.preferredBuildings))
			return false;
		if (!Arrays.equals(preferredDepartments, other.preferredDepartments))
			return false;
		if (!Arrays.equals(preferredRooms, other.preferredRooms))
			return false;
		return true;
	}
	
}
