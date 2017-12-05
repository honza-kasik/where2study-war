package cz.honzakasik.upol.where2study.users;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.bind.JAXBException;

import cz.honzakasik.upol.where2study.restclients.DataDownloader;

@RequestScoped
@ManagedBean
public class UserController {
	
	@EJB
	private UserManager userManager;
	
	@EJB
	private DataDownloader dataDownloader;
	
	public void downloadAllData() throws JAXBException, IllegalStateException, IOException {
		dataDownloader.downloadData();
	}
	
	public User findUser(String email, String passwordHash) throws Exception {
		return userManager.findUser(email, passwordHash);
	}
	
}
