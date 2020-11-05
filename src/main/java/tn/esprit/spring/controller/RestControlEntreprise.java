package tn.esprit.spring.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

@RestController
public class RestControlEntreprise {
	private static final Logger l = Logger.getLogger(RestControlEntreprise.class);

	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimesheetService itimesheetservice;

	// Ajouter Entreprise :
	// http://localhost:8081/SpringMVC/servlet/ajouterEntreprise
	


	// http://localhost:8081/SpringMVC/servlet/affecterDepartementAEntreprise/1/1
	@PutMapping(value = "/affecterDepartementAEntreprise/{iddept}/{identreprise}")
	public void affecterDepartementAEntreprise(@PathVariable("iddept") int depId,
			@PathVariable("identreprise") int entrepriseId) {
		ientrepriseservice.affecterDepartementAEntreprise(depId, entrepriseId);
		l.info("departement affecté ");
	}

	// http://localhost:8081/SpringMVC/servlet/deleteEntrepriseById/1
	@DeleteMapping("/deleteEntrepriseById/{identreprise}")
	@ResponseBody
	public void deleteEntrepriseById(@PathVariable("identreprise") int entrepriseId) {
		ientrepriseservice.deleteEntrepriseById(entrepriseId);
		l.info("entreprise supprimée ");
	}

	// http://localhost:8081/SpringMVC/servlet/getEntrepriseById/1
	@GetMapping(value = "getEntrepriseById/{identreprise}")
	@ResponseBody
	public Entreprise getEntrepriseById(@PathVariable("identreprise") int entrepriseId) {

		return ientrepriseservice.getEntrepriseById(entrepriseId);
	}

	// http://localhost:8081/SpringMVC/servlet/ajouterDepartement
	

	

	// http://localhost:8081/SpringMVC/servlet/getAllDepartementsNamesByEntreprise/1
	@GetMapping(value = "getAllDepartementsNamesByEntreprise/{identreprise}")
	@ResponseBody
	public List<String> getAllDepartementsNamesByEntreprise(@PathVariable("identreprise") int entrepriseId) {
		l.info("les noms des departements par entreprise");
		return ientrepriseservice.getAllDepartementsNamesByEntreprise(entrepriseId);

	}

	
}