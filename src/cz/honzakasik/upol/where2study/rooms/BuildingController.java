package cz.honzakasik.upol.where2study.rooms;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import cz.honzakasik.upol.where2study.room.Building;
import cz.honzakasik.upol.where2study.room.BuildingManager;

/**
 * Controller for building related tasks
 */
@RequestScoped
@ManagedBean
public class BuildingController {
	
	@EJB
	private BuildingManager buildingManager;
	
	public List<Building> getAllBuildings() {
		return buildingManager.getAllBuildings();
	}

}
