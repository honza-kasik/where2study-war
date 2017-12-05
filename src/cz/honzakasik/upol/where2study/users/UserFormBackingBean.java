package cz.honzakasik.upol.where2study.users;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import cz.honzakasik.upol.where2study.room.Building;
import cz.honzakasik.upol.where2study.room.Department;
import cz.honzakasik.upol.where2study.room.Room;

@RequestScoped
@ManagedBean
public class UserFormBackingBean {
	
	private String email = "";
	private String firstName = "";
	private String lastName = "";
	
	private String password = "";
	
	private List<Building> prefferedBuildings = new ArrayList<>();
	
	private List<Department> prefferedDepartments = new ArrayList<>();
	
	private List<Room> prefferedRooms = new ArrayList<>();

	public UserFormBackingBean() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Building> getPrefferedBuildings() {
		return prefferedBuildings;
	}

	public void setPrefferedBuildings(List<Building> prefferedBuildings) {
		this.prefferedBuildings = prefferedBuildings;
	}

	public List<Department> getPrefferedDepartments() {
		return prefferedDepartments;
	}

	public void setPrefferedDepartments(List<Department> prefferedDepartments) {
		this.prefferedDepartments = prefferedDepartments;
	}

	public List<Room> getPrefferedRooms() {
		return prefferedRooms;
	}

	public void setPrefferedRooms(List<Room> prefferedRooms) {
		this.prefferedRooms = prefferedRooms;
	}

}
