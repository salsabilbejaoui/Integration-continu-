package tn.esprit.spring.dto;

import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;

public class DepartementDTO {
	
private String name1;
	
	//@JsonManagedReference 
	@JsonIgnore
	@ManyToMany
	private List<Employe> employes1;
	
	@OneToMany(mappedBy="departement")
	private List<Mission> missions1;
	
	@ManyToOne
	private Entreprise entreprise1;

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public List<Employe> getEmployes1() {
		return employes1;
	}

	public void setEmployes1(List<Employe> employes1) {
		this.employes1 = employes1;
	}

	public List<Mission> getMissions1() {
		return missions1;
	}

	public void setMissions1(List<Mission> missions1) {
		this.missions1 = missions1;
	}

	public Entreprise getEntreprise1() {
		return entreprise1;
	}

	public void setEntreprise1(Entreprise entreprise1) {
		this.entreprise1 = entreprise1;
	}

	
	

}
