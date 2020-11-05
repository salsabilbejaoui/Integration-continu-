package tn.esprit.spring.mapper;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.esprit.spring.dto.ContratDTO;
import tn.esprit.spring.dto.DepartementDTO;
import tn.esprit.spring.dto.EmployeDTO;
import tn.esprit.spring.dto.EntrepriseDTO;
import tn.esprit.spring.dto.MissionDTO;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;


@Component
public class TimesheetMapper {

	@Autowired
    private ConfiguredModelMapper modelMapper;

	public Employe mapEmployeDtoToEmploye(EmployeDTO employedto) {
		Employe mappedEmploye = modelMapper.map(employedto , Employe.class);
		mappedEmploye.setActif(employedto.isActif1());
		mappedEmploye.setContrat(employedto.getContrat1());
		mappedEmploye.setDepartements(employedto.getDepartements1());
		mappedEmploye.setEmail(employedto.getEmail1());
		mappedEmploye.setNom(employedto.getNom1());
		mappedEmploye.setPassword(employedto.getPassword1());
		mappedEmploye.setPrenom(employedto.getPrenom1());
		mappedEmploye.setRole(employedto.getRole1());
		mappedEmploye.setTimesheets(employedto.getTimesheets1());
			return mappedEmploye;
	}
	public Contrat mapContratDtoToContrat(ContratDTO contratdto) {
		Contrat mappedContrat = modelMapper.map(contratdto, Contrat.class);
		mappedContrat.setDateDebut(contratdto.getDateDebut1());
		mappedContrat.setEmploye(contratdto.getEmploye1());
		mappedContrat.setSalaire(contratdto.getSalaire1());
		mappedContrat.setTypeContrat(contratdto.getTypeContrat1());
			return mappedContrat;
	}
	
	public Entreprise mapEntrepriseDtoToEntreprise(EntrepriseDTO entreprisedto) {
		Entreprise mappedEntreprise = modelMapper.map(entreprisedto, Entreprise.class);
		mappedEntreprise.setDepartements(entreprisedto.getDepartements1());
		mappedEntreprise.setName(entreprisedto.getName1());
		mappedEntreprise.setRaisonSocial(entreprisedto.getRaisonSocial1());
			return mappedEntreprise;
	}
	
	public Departement mapDepartementDtoToDepartement(DepartementDTO departementdto) {
		Departement mappedDepartement = modelMapper.map(departementdto, Departement.class);
		mappedDepartement.setEmployes(departementdto.getEmployes1());
		mappedDepartement.setEntreprise(departementdto.getEntreprise1());
		mappedDepartement.setMissions(departementdto.getMissions1());
		mappedDepartement.setName(departementdto.getName1());
			return mappedDepartement;
	}
	
	public Mission mapMissionDtoToMission(MissionDTO missiondto) {
		Mission mappedMission = modelMapper.map(missiondto, Mission.class);
		mappedMission.setDepartement(missiondto.getDepartement1());
		mappedMission.setDescription(missiondto.getDescription1());
		mappedMission.setName(missiondto.getName1());
		mappedMission.setTimesheets(missiondto.getTimesheets1());
			return mappedMission;
	}
}


