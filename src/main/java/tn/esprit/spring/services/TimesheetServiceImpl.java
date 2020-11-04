package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
	private static final Logger l = LogManager.getLogger(TimesheetServiceImpl.class);


	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	
	public int ajouterMission(Mission mission) {
		missionRepository.save(mission);
		return mission.getId();
	}
    
	public void affecterMissionADepartement(int missionId, int depId) {
		Mission mission = missionRepository.findById(missionId).get();
		Departement dep = deptRepoistory.findById(depId).get();
		mission.setDepartement(dep);
		missionRepository.save(mission);
		
	}

	public Timesheet ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		l.info("ajouter Timesheet pour l'mployee : "+employeId+"et la mission "+missionId); 
		TimesheetPK timesheetPK = new TimesheetPK();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		return timesheetRepository.save(timesheet);
		
	}

	public Timesheet validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		l.info("dans validerTimesheet pour l employer "+employeId);
		
		Employe validateur = employeRepository.findById(validateurId).get();
		Mission mission = missionRepository.findById(missionId).get();
		//verifier s'il est un chef de departement (interet des enum)
		if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
			l.info("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return null;
		}
		//verifier s'il est le chef de departement de la mission en question;
		boolean chefDeLaMission = false;
		for(Departement dep : validateur.getDepartements()){
			if(dep.getId() == mission.getDepartement().getId()){
				chefDeLaMission = true;
				break;
			}
		}
		if(!chefDeLaMission){
			l.info("l'employe doit etre chef de departement de la mission en question");
			return null;
		}
		l.info("tout les condition sont vÃ©rifier");
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		timesheetRepository.save(timesheet);
		//Comment Lire une date de la base de donnÃ©es
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		l.info("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));
		return timesheet;
	}
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		l.info("dans getTimeshhetBYMission and Date de l'employer ");
		l.info(" sortie de gettimesheetbymISSIONet date ");
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}
	
	
public List<Timesheet> retrieveAlltimesheet() {
		
		l.info("In  retrieveAlltimesheet : "); 
		List<Timesheet> timesheets = (List<Timesheet>) timesheetRepository.findAll();  
		for (Timesheet timesheet : timesheets) {
			l.debug("Timesheet +++ : " + timesheet);
		}
		l.info("Out of retrieveAlltimesheets."); 
		return timesheets;

		
	}

	
	public void deletetTimesheetById(int employeId) {
		// TODO Auto-generated method stub
		
	}


	
	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		return timesheetRepository.getAllEmployeByMission(missionId);
	}

}
