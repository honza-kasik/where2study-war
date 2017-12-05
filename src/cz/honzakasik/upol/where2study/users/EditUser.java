package cz.honzakasik.upol.where2study.users;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

@RequestScoped
@ManagedBean
public class EditUser {
		
	@Inject
	private UserFormBackingBean userFormBackupBean;
	
	@Inject
	private Login login;
	
	@EJB
	private UserManager userManager;
	
	public EditUser() {
	}
	
	public UserFormBackingBean getUserFormBackupBean() {
		return userFormBackupBean;
	}
	
	public void setUserFormBackupBean(UserFormBackingBean userFormBackupBean) {
		this.userFormBackupBean = userFormBackupBean;
	}
	
	public UserFormBackingBean getPopulatedBackingBean() throws Exception {
		User user = userManager.findUser(login.getCurrentUser().getEmail(), login.getCurrentUser().getPasswordHash());
		UserFormBackingBean userFormBackupBean = new UserFormBackingBean();
		userFormBackupBean.setEmail(user.getEmail());
		userFormBackupBean.setFirstName(user.getFirstName());
		userFormBackupBean.setLastName(user.getLastName());
		userFormBackupBean.setPrefferedBuildings(user.getPrefferedBuildings());
		userFormBackupBean.setPrefferedDepartments(user.getPrefferedDepartments());
		userFormBackupBean.setPrefferedRooms(user.getPrefferedRooms());
		return userFormBackupBean;
	}

	public String register() {
		User user = new User();
		user.setEmail(userFormBackupBean.getEmail());
		user.setFirstName(userFormBackupBean.getFirstName());
		user.setLastName(userFormBackupBean.getLastName());
		if (userFormBackupBean.getPassword() != null) {
			user.setPasswordHash(UserUtils.getPasswdHash(userFormBackupBean.getPassword()));
		}
		user.setPrefferedBuildings(userFormBackupBean.getPrefferedBuildings());
		user.setPrefferedDepartments(userFormBackupBean.getPrefferedDepartments());
		user.setPrefferedRooms(userFormBackupBean.getPrefferedRooms());
		userManager.createUser(user);
		return "index";
	}
	
	public void editUser() throws Exception {
		User user = userManager.findUser(login.getCurrentUser().getEmail(), login.getCurrentUser().getPasswordHash());
		user.setEmail(userFormBackupBean.getEmail());
		user.setFirstName(userFormBackupBean.getFirstName());
		user.setLastName(userFormBackupBean.getLastName());
		if (userFormBackupBean.getPassword() != null && !userFormBackupBean.getPassword().isEmpty()) {
			user.setPasswordHash(UserUtils.getPasswdHash(userFormBackupBean.getPassword()));
		}
		user.setPrefferedBuildings(userFormBackupBean.getPrefferedBuildings());
		user.setPrefferedDepartments(userFormBackupBean.getPrefferedDepartments());
		user.setPrefferedRooms(userFormBackupBean.getPrefferedRooms());
		userManager.saveUser(user);
	}

}
