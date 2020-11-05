package tn.esprit.spring.dto;

import java.util.Date;

import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tn.esprit.spring.entities.Employe;

public class ContratDTO {
	
	@Temporal(TemporalType.DATE)
	private Date dateDebut1;

	private String typeContrat1;

	

	@OneToOne
	private Employe employe1;

	private float salaire1;

	public Date getDateDebut1() {
		return dateDebut1;
	}

	public void setDateDebut1(Date dateDebut1) {
		this.dateDebut1 = dateDebut1;
	}

	public String getTypeContrat1() {
		return typeContrat1;
	}

	public void setTypeContrat1(String typeContrat1) {
		this.typeContrat1 = typeContrat1;
	}

	public Employe getEmploye1() {
		return employe1;
	}

	public void setEmploye1(Employe employe1) {
		this.employe1 = employe1;
	}

	public float getSalaire1() {
		return salaire1;
	}

	public void setSalaire1(float salaire1) {
		this.salaire1 = salaire1;
	}

	
	
	

}
