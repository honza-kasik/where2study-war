package cz.honzakasik.upol.where2study.users;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Named
public class TestService {
	
	public void runTest() {
		System.out.println("Started obtining data!");
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("https://stagservices.upol.cz/ws/services/rest/mistnost/getMistnostiInfo");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		} else {
			System.out.println(response.readEntity(String.class));
		}
	}

}
