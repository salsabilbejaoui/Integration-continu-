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
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for (Departement dep : entrepriseManagedEntity.getDepartements()) {
			depNames.add(dep.getName());
		}
		l.debug("Liste des departements par entreprise! ");
		return depNames;
	}
	

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());
		l.info("entreprise supprimée!");
		Optional<Entreprise> entrepriseop = this.entrepriseRepoistory.findById(entrepriseId);
		if (entrepriseop.isPresent()) {
			entrepriseRepoistory.delete(entrepriseop.get());
		}

	}

	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).get());
		l.info("departement supprimé!");
		Optional<Departement> departementoptional = this.deptRepoistory.findById(depId);
		if (departementoptional.isPresent()) {
			deptRepoistory.delete(departementoptional.get());
		}
	}

	public Entreprise getEntrepriseById(int entrepriseId) {
		return entrepriseRepoistory.findById(entrepriseId).get();

		
	

	}
}
