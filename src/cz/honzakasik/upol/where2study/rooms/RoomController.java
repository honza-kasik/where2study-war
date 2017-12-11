package cz.honzakasik.upol.where2study.rooms;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import cz.honzakasik.upol.where2study.room.Room;
import cz.honzakasik.upol.where2study.room.RoomManager;
import cz.honzakasik.upol.where2study.schedule.Event;
import cz.honzakasik.upol.where2study.users.Login;

/**
 * Controller for room related tasks
 */
@RequestScoped
@ManagedBean
public class RoomController {

	@EJB
	private RoomManager roomManager;
	
	@ManagedProperty(value = "#{login}")
	private Login login;

	static final Comparator<Room> START_TIME_COMPARATOR = (r1, r2) -> {
		Event e1 = r1.getSchedule().getNextEventStartingAfterNow();
		Event e2 = r2.getSchedule().getNextEventStartingAfterNow();
		if (e1 == null && e2 != null) {
			return 1;
		} else if (e1 != null && e2 == null) {
			return -1;
		} else if (e1 == null && e2 == null) {
			return 0;
		}
		LocalTime r1StartTime = r1.getSchedule().getNextEventStartingAfterNow().getStartTime();
		LocalTime r2StartTime = r2.getSchedule().getNextEventStartingAfterNow().getStartTime();
		return r1StartTime.compareTo(r2StartTime);
	};

	public List<Room> getAllFreeRoomsNow() {
		return roomManager.getAllFreeRoomsNow();
	}

	/**
	 * Get limit of rooms sorted by time to next starting event
	 * 
	 * @param limit
	 *            number to limit events
	 * @return
	 */
	public List<Room> getLimitFreeRoomsSortedByTimeToNextEvent(int limit) {
		return roomManager.getAllFreeRoomsNow().stream()
				.sorted(START_TIME_COMPARATOR)
				.limit(limit)
				.collect(Collectors.toList());
	}	

	public List<Room> getLimitFreeRoomsSortedByTimeToNextEventByLoggedUserPreferences(int limit) {
		return roomManager.getAllFreeRoomsNowBasedOnUserPrefs(login.getCurrentUser()).stream()
				.sorted(START_TIME_COMPARATOR)
				.limit(limit)
				.collect(Collectors.toList());
	}

	/**
	 * Get all rooms
	 * 
	 * @return
	 */
	public List<Room> allRooms() {
		return roomManager.getAllRooms();
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
}
