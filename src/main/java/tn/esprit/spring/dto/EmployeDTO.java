package tn.esprit.spring.dto;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;

public class EmployeDTO {
	
private String prenom1;
	
	private String nom1;
	
	//@Column(unique=true)
	//@Pattern(regex=".+\@.+\..+")
	private String email1;

	private String password1;
	
	private boolean actif1;
	
	@Enumerated(EnumType.STRING)
	//@NotNull
	private Role role1;
	
	//@JsonBackReference  
	@JsonIgnore
	@ManyToMany(mappedBy="employes",fetch=FetchType.EAGER )
	//@NotNull
	private List<Departement> departements1;
	
	@JsonIgnore
	//@JsonBackReference
	@OneToOne(mappedBy="employe")
	private Contrat contrat1;
	
	@JsonIgnore
	//@JsonBackReference
	@OneToMany(mappedBy="employe")
	private List<Timesheet> timesheets1;

	public String getPrenom1() {
		return prenom1;
	}

	public void setPrenom1(String prenom1) {
		this.prenom1 = prenom1;
	}

	public String getNom1() {
		return nom1;
	}

	public void setNom1(String nom1) {
		this.nom1 = nom1;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public boolean isActif1() {
		return actif1;
	}

	public void setActif1(boolean actif1) {
		this.actif1 = actif1;
	}

	public Role getRole1() {
		return role1;
	}

	public void setRole1(Role role1) {
		this.role1 = role1;
	}

	public List<Departement> getDepartements1() {
		return departements1;
	}

	public void setDepartements1(List<Departement> departements1) {
		this.departements1 = departements1;
	}

	public Contrat getContrat1() {
		return contrat1;
	}

	public void setContrat1(Contrat contrat1) {
		this.contrat1 = contrat1;
	}

	public List<Timesheet> getTimesheets1() {
		return timesheets1;
	}

	public void setTimesheets1(List<Timesheet> timesheets1) {
		this.timesheets1 = timesheets1;
	}

	
}
