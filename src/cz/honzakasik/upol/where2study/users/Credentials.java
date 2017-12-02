package cz.honzakasik.upol.where2study.users;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class Credentials implements Serializable {
	
	private static final long serialVersionUID = 4728443359208315946L;
	
	private String email;
	private String passwordHash;
	
	public Credentials() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}	

}
