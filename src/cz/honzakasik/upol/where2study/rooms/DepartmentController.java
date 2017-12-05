package cz.honzakasik.upol.where2study.rooms;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import cz.honzakasik.upol.where2study.room.Department;
import cz.honzakasik.upol.where2study.room.DepartmentManager;

@RequestScoped
@ManagedBean
public class DepartmentController {

	@EJB
	private DepartmentManager departmentManager;
	
	public List<Department> getAllDepartments() {
		return departmentManager.getAllDepartments();
	}
}
