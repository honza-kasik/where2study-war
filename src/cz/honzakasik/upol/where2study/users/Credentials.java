package cz.honzakasik.upol.where2study.users;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Credential class which main use is to trnasport username and password from view to controller
 */
@RequestScoped
@Named
public class Credentials implements Serializable {
	
	private static final long serialVersionUID = 4728443359208315946L;
	
	private String email;
	private String password;
	
	public Credentials() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

}
