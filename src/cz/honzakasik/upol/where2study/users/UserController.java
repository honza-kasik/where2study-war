package cz.honzakasik.upol.where2study.users;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Controller handling user related tasks
 */
@RequestScoped
@ManagedBean
public class UserController {
	
	@EJB
	private UserManager userManager;
		
	public User findUser(String email, String passwordHash) throws Exception {
		return userManager.findUser(email, passwordHash);
	}
	
}
