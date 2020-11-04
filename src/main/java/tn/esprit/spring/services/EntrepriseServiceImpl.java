package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);

	@Autowired
	EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	public int ajouterEntreprise(Entreprise entreprise) {
		l.debug("Je viens de lancer l'ajout des entreprises! ");
		entrepriseRepoistory.save(entreprise);
		l.info("Ajouté !");
		return entreprise.getId();

	}

	public int ajouterDepartement(Departement dep) {
		l.debug("Je viens de lancer l'ajout des departements! ");
		deptRepoistory.save(dep);
		l.info("Ajouté !");
		return dep.getId();
	}

	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		Optional<Departement> departementop = this.deptRepoistory.findById(depId);
		Optional<Entreprise> entrepriseop = this.entrepriseRepoistory.findById(entrepriseId);
		if (departementop.isPresent() && entrepriseop.isPresent()) {
			Entreprise entrepriseManagedEntity = entrepriseop.get();
			Departement depManagedEntity = departementop.get();
			depManagedEntity.setEntreprise(entrepriseManagedEntity);
			l.debug("Departmement affecté a l'entreprise! ");
			deptRepoistory.save(depManagedEntity);

		}
	}

	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		l.debug("methode getAllDepartementsNamesByEntreprise ");
		List<String> depNames = new ArrayList<>();
		try {
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
			
			if(entrepriseManagedEntity!=null && entrepriseManagedEntity.getDepartements()!=null){
			for(Departement dep : entrepriseManagedEntity.getDepartements()){
				depNames.add(dep.getName());
			}
			l.debug("getAllDepartementsNamesByEntreprise fini avec succes ");
			return depNames;
			}
			else {
				l.error("erreur methode getAllDepartementsNamesByEntreprise : " );
				return depNames;
			}
		} catch (Exception e) {
			l.error("erreur methode getAllDepartementsNamesByEntreprise : " +e);
			return depNames;
		}
	}
	

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		
		l.info("entreprise supprimée!");
		Optional<Entreprise> entrepriseop = this.entrepriseRepoistory.findById(entrepriseId);
		if (entrepriseop.isPresent()) {
			Entreprise entrepriseManagedEntity = entrepriseop.get();
			entrepriseRepoistory.delete(entrepriseManagedEntity);
		}

	}



	public Entreprise getEntrepriseById(int entrepriseId) {
		l.debug("methode getEntrepriseById ");
		
		
		try {
			Entreprise et= entrepriseRepoistory.findById(entrepriseId).orElse(null);
			l.debug("getEntrepriseById fini avec succes ");
			return et;
		} catch (Exception e) {
			l.error("erreur methode getEntrepriseById : " +e);
			return null;
		}

		
	

	}

	
}
