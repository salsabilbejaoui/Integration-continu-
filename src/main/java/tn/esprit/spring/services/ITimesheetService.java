package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;



public interface ITimesheetService {
	
	public int ajouterMission(Mission mission);
	public void affecterMissionADepartement(int missionId, int depId);
	public Timesheet ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin);
	public Timesheet validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId);
	public List<Mission> findAllMissionByEmployeJPQL(int employeId);
	public List<Employe> getAllEmployeByMission(int missionId);
	public void deletetTimesheetById(int employeId) ;
	
}
