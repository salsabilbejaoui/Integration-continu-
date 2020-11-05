package tn.esprit.spring.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import tn.esprit.spring.entities.Departement;

public class EntrepriseDTO {
	
private String name1;
	
	
	private String raisonSocial1;
	
	@OneToMany(mappedBy="entreprise", 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
			fetch=FetchType.EAGER)
	private List<Departement> departements1 = new ArrayList<>();

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getRaisonSocial1() {
		return raisonSocial1;
	}

	public void setRaisonSocial1(String raisonSocial1) {
		this.raisonSocial1 = raisonSocial1;
	}

	public List<Departement> getDepartements1() {
		return departements1;
	}

	public void setDepartements1(List<Departement> departements1) {
		this.departements1 = departements1;
	}

	
	

}
