package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




@Service
public class EmployeServiceImpl implements IEmployeService {
	

	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	
	
	
	@Override
	public int ajouterEmploye(Employe employe) {
		l.info("In ajouterEmploye");
		employeRepository.save(employe);
		l.info("Out ajouterEmploye");
		
		return employe.getId();
	}
	
	
	
	
	@Override
	public Employe authenticate(String login, String password) {
		l.info("In authenticate");
		Employe emp =employeRepository.getEmployeByEmailAndPassword(login, password);
		l.info("Out authenticate");
		return emp;
	}

	@Override
	public Employe addOrUpdateEmploye(Employe employe) {
		l.info("In addOrUpdateEmploye");
		Employe emp =employeRepository.save(employe);
		l.info("out addOrUpdateEmploye");
		return emp;
	}

	public Employe mettreAjourEmailByEmployeId(String email, int employeId) {
		l.info("In mettreAjourEmailByEmployeId");
		Employe employe = employeRepository.findById(employeId).orElse(null);
		if(employe != null) {
			employe.setEmail(email);
			employeRepository.save(employe);
		}
		l.info("out mettreAjourEmailByEmployeId");
		return employe;

	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		l.info("In affecterEmployeADepartement");
		Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
		Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);

		if(depManagedEntity!=null && employeManagedEntity!=null) {
			if(depManagedEntity.getEmployes() == null){

				List<Employe> employes = new ArrayList<>();
				employes.add(employeManagedEntity);
				depManagedEntity.setEmployes(employes);
			}else{

				depManagedEntity.getEmployes().add(employeManagedEntity);
			}
			deptRepoistory.save(depManagedEntity); 
		}
		l.info("out affecterEmployeADepartement");
		

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		l.info("In desaffecterEmployeDuDepartement");
		Departement dep = deptRepoistory.findById(depId).orElse(null);
		if(dep!=null) {
			int employeNb = dep.getEmployes().size();
			for(int index = 0; index < employeNb; index++){
				if(dep.getEmployes().get(index).getId() == employeId){
					dep.getEmployes().remove(index);
					break;
				}
			}
		}
		l.info("out desaffecterEmployeDuDepartement");
	} 
	
	// Tablesapce (espace disque) 

	public int ajouterContrat(Contrat contrat) {
		l.info("In ajouterContrat");
		contratRepoistory.save(contrat);
		int c=  contrat.getReference();
		l.info("out ajouterContrat");
		return c;
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		l.info("In affecterContratAEmploye");
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElse(null);
		Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
		if(contratManagedEntity!=null && employeManagedEntity!=null) {
			contratManagedEntity.setEmploye(employeManagedEntity);
			contratRepoistory.save(contratManagedEntity);
		}
		
		l.info("Out affecterContratAEmploye");


	}

	public String getEmployePrenomById(int employeId) {
		l.info("In getEmployePrenomById");

		Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
		if(employeManagedEntity!=null) {
			return employeManagedEntity.getPrenom();
		}
		l.info("out getEmployePrenomById");

		return "Nothing found";

	}
	 
	public Boolean deleteEmployeById(int employeId)
	{
		l.info("In deleteEmployeById");

		try {
			Employe employe = employeRepository.findById(employeId).orElse(null);

			if(employe!=null) {
				for(Departement dep : employe.getDepartements()){
					dep.getEmployes().remove(employe);
				}

				employeRepository.delete(employe);
				l.info("out deleteEmployeById");
				return true;
			}
			return false;
		} catch (Exception e) {
			l.error("Exception " +e);
			return false;
		}

		
	}

	public void deleteContratById(int contratId) {
		l.info("in deleteContratById");
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElse(null);
		if(contratManagedEntity!=null) {
			contratRepoistory.delete(contratManagedEntity);
		}
		l.info("out deleteContratById");

	}

	public int getNombreEmployeJPQL() {
		l.info("in getNombreEmployeJPQL");
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		l.info("in getAllEmployeNamesJPQL");
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		l.info("in getAllEmployeByEntreprise");
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		l.info("in mettreAjourEmailByEmployeIdJPQL");

		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);
		l.info("out mettreAjourEmailByEmployeIdJPQL");


	}
	public void deleteAllContratJPQL() {
		l.info("in deleteAllContratJPQL");
		employeRepository.deleteAllContratJPQL();
		l.info("out deleteAllContratJPQL");

	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		l.info("in getSalaireByEmployeIdJPQL");

		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		l.info("in getSalaireMoyenByDepartementId");

		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		l.info("in getTimesheetsByMissionAndDate");

		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		l.info("in getAllEmployes");

		return (List<Employe>) employeRepository.findAll();
	}

}