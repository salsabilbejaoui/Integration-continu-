package tn.esprit.spring.dto;

import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Timesheet;

public class MissionDTO {
	
private String name1;
	
	private String description1;
	
	@ManyToOne
	private Departement departement1;
	
	@OneToMany(mappedBy="mission")
	private  List<Timesheet> timesheets1;

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public Departement getDepartement1() {
		return departement1;
	}

	public void setDepartement1(Departement departement1) {
		this.departement1 = departement1;
	}

	public List<Timesheet> getTimesheets1() {
		return timesheets1;
	}

	public void setTimesheets1(List<Timesheet> timesheets1) {
		this.timesheets1 = timesheets1;
	}

	

}
