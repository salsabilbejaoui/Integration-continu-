package tn.esprit.spring.controller;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IEmployeService;


@Scope(value = "session")
@Controller(value = "employeController")
@ELBeanName(value = "employeController")
@Join(path = "/", to = "/login.jsf")
public class ControllerEmployeImpl  {

	@Autowired
	IEmployeService employeService;

	private String login; 
	private String password2; 
	private Boolean loggedIn2;

	private Employe authenticatedUser = null; 
	private String prenom2; 
	private String nom2; 
	private String email2;
	private boolean actif2;
	private Role role2;  
	public Role[] getRoles() { return Role.values(); }

	private List<Employe> employes; 


	private Integer employeIdToBeUpdated; 
	
	String loginUrl= "/login.xhtml?faces-redirect=true";


	public String doLogin() {

		String navigateTo = "null";
		authenticatedUser=employeService.authenticate(login, password2);
		if (authenticatedUser != null && authenticatedUser.getRole() == Role.ADMINISTRATEUR) {
			navigateTo = "/pages/admin/welcome.xhtml?faces-redirect=true";
			loggedIn2 = true;
		}		

		else
		{
			
			FacesMessage facesMessage =
					new FacesMessage("Login Failed: Please check your username/password and try again.");
			FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		}
		return navigateTo;	
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Boolean getLoggedIn2() {
		return loggedIn2;
	}

	public void setLoggedIn2(Boolean loggedIn2) {
		this.loggedIn2 = loggedIn2;
	}

	public String getPrenom2() {
		return prenom2;
	}

	public void setPrenom2(String prenom2) {
		this.prenom2 = prenom2;
	}

	public String getNom2() {
		return nom2;
	}

	public void setNom2(String nom2) {
		this.nom2 = nom2;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public boolean isActif2() {
		return actif2;
	}

	public void setActif2(boolean actif2) {
		this.actif2 = actif2;
	}

	public Role getRole2() {
		return role2;
	}

	public void setRole2(Role role2) {
		this.role2 = role2;
	}

	public String doLogout()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	
	return "/login.xhtml?faces-redirect=true";
	}


	public String addEmploye() {

		if (authenticatedUser==null || !loggedIn2) return loginUrl;

		employeService.addOrUpdateEmploye(new Employe(nom2, prenom2, email2, password2, actif2, role2)); 
		return "null"; 
	}  

	public String removeEmploye(int employeId) {
		String navigateTo = "null";
		if (authenticatedUser==null || !loggedIn2) return loginUrl;

		employeService.deleteEmployeById(employeId);
		return navigateTo; 
	} 

	public String displayEmploye(Employe empl) 
	{
		String navigateTo = "null";
		if (authenticatedUser==null || !loggedIn2) return loginUrl;


		this.setPrenom2(empl.getPrenom());
		this.setNom2(empl.getNom());
		this.setActif2(empl.isActif()); 
		this.setEmail2(empl.getEmail());
		this.setRole2(empl.getRole());
		this.setPassword2(empl.getPassword());
		this.setEmployeIdToBeUpdated(empl.getId());

		return navigateTo; 

	} 

	public String updateEmploye() 
	{ 
		String navigateTo = "null";
		
		if (authenticatedUser==null || !loggedIn2) return loginUrl;

		employeService.addOrUpdateEmploye(new Employe(employeIdToBeUpdated, nom2, prenom2, email2, password2, actif2, role2)); 

		return navigateTo; 

	}

	public IEmployeService getEmployeService() {
		return employeService;
	}

	public void setEmployeService(IEmployeService employeService) {
		this.employeService = employeService;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}



	public Employe getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(Employe authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

	

	public List<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}

	public Integer getEmployeIdToBeUpdated() {
		return employeIdToBeUpdated;
	}

	public void setEmployeIdToBeUpdated(Integer employeIdToBeUpdated) {
		this.employeIdToBeUpdated = employeIdToBeUpdated;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	} 


	

}