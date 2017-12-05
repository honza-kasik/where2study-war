package cz.honzakasik.upol.where2study.rooms;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import cz.honzakasik.upol.where2study.room.Room;
import cz.honzakasik.upol.where2study.room.RoomManager;
import cz.honzakasik.upol.where2study.schedule.Event;

@RequestScoped
@ManagedBean
public class RoomController {
	
	@EJB
	private RoomManager roomManager;

	public List<Room> getAllFreeRoomsNow() {
		return roomManager.getAllFreeRoomsNow();
	}
	
	public List<Room> getLimitFreeRoomsSortedByTimeToNextEvent(int limit) {
		return roomManager.getAllFreeRoomsNow().stream().sorted((r1, r2) -> {
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
		}).limit(limit).collect(Collectors.toList());
	}
	
	public List<Room> allRooms() {
		return roomManager.getAllRooms();
	}
}
