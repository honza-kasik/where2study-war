package cz.honzakasik.upol.where2study.users;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.honzakasik.upol.where2study.room.Building;
import cz.honzakasik.upol.where2study.room.BuildingManager;
import cz.honzakasik.upol.where2study.room.Department;
import cz.honzakasik.upol.where2study.room.DepartmentManager;
import cz.honzakasik.upol.where2study.room.Room;
import cz.honzakasik.upol.where2study.room.RoomManager;

@ViewScoped
@ManagedBean
public class UserDataBean implements Serializable {

	private static final long serialVersionUID = -3832277186724298077L;

	private static final Logger log = LoggerFactory.getLogger(UserDataBean.class);
	
	private static final String[] EMPTY_STRING_ARRAY = {};
	
	//CDI vs JSF - not possible to use Inject here, since the injected bean is not a singleton (will not behave as one)
	@ManagedProperty(value = "#{login}")
	private Login login;
	
	@EJB
	private UserManager userManager;
	
	@EJB
	private BuildingManager buildingManager;
	
	@EJB
	private DepartmentManager departmentManager;
	
	@EJB
	private RoomManager roomManager;
	
	private Integer id;
	
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	
	private String[] preferredBuildings;
	private String[] preferredDepartments;
	private String[] preferredRooms;
	
	/**
	 * Populates this bean from logged user's instance
	 */
	@Transactional
	public void populateWithLoggedUserData() {
		if (login.isLoggedIn()) {
			final User u = userManager.findUser(login.getCurrentUser().getId());
			id = u.getId();
			firstName = u.getFirstName();
			lastName = u.getLastName();
			email = u.getEmail();
			preferredBuildings = buildingSetToIdArray(u.getPrefferedBuildings());
			preferredDepartments = departmentSetToIdArray(u.getPrefferedDepartments());
			preferredRooms = roomSetToIdArray(u.getPrefferedRooms());
		} else {
			log.warn("Accessed to user edit form with no logged user!");
		}
	}
	
	/**
	 * Save edited user data
	 */
	@Transactional
	public void save() {
		if (this.id != null) {
			final User u = userManager.findUser(id);
			if (email != null || !email.isEmpty()) u.setEmail(email);
			log.info("First name: {}", firstName);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setPrefferedBuildings(idArrayToBuildingSet(preferredBuildings));
			u.setPrefferedDepartments(idArrayToDepartmentSet(preferredDepartments));
			u.setPrefferedRooms(idArrayToRoomSet(preferredRooms));
			userManager.saveUser(u);
		} else {
			log.warn("ID is null, bean probably wasn't populated before! Nothing will be saved!");
		}
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
	
	//TODO make this a converter
	private String[] buildingSetToIdArray(Set<Building> buildings) {
		if (buildings == null || buildings.isEmpty()) {
			return EMPTY_STRING_ARRAY;
		}
		String[] buildingIds = new String[buildings.size()];
		int i = 0;
		for (Building building : buildings) {
			buildingIds[i] = building.getAbbreviation();
			i++;
		}
		return buildingIds;
	}
	
	//TODO make this a converter
	private Set<Building> idArrayToBuildingSet(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new HashSet<>();
		}
		Set<Building> buildings = new HashSet<>();
		for (String id : ids) {
			buildings.add(buildingManager.getBuildingById(id));
		}
		return buildings;
		
	}
	
	//TODO make this a converter
	private String[] departmentSetToIdArray(Set<Department> departments) {
		String[] departmentIds = new String[departments.size()];
		int i = 0;
		for (Department department : departments) {
			departmentIds[i] = department.getAbbreviation();
			i++;
		}
		return departmentIds;
	}
	
	//TODO make this a converter
	private Set<Department> idArrayToDepartmentSet(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new HashSet<>();
		}
		Set<Department> departments = new HashSet<>();
		for (String id : ids) {
			departments.add(departmentManager.getDepartmentById(id));
		}
		return departments;
	}
	
	//TODO make this a converter
	private String[] roomSetToIdArray(Set<Room> rooms) {
		String[] roomIds = new String[rooms.size()];
		int i = 0;
		for (Room room : rooms) {
			roomIds[i] = String.valueOf(room.getId());
			i++;
		}
		return roomIds;
	}
	
	private Set<Room> idArrayToRoomSet(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new HashSet<>();
		}
		Set<Room> rooms = new HashSet<>();
		for (String id : ids) {
			rooms.add(roomManager.getRoom(Integer.valueOf(id)));
		}
		return rooms;
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
