package tn.esprit.spring.services;

import org.junit.Assert.*;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {
	
	@Autowired
	IEmployeService iEmployeService;
	@Autowired
	EmployeRepository repo ;
	@Autowired
	EmployeServiceImpl employeServiceImpl;
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository dep ;
	@Autowired
	ContratRepository contratRepository;
	
	@Test
	public void TestEmployeAuthenticate() {
		Employe authenticateEmploye = employeServiceImpl.authenticate("iheb2@esprit.tn", "azerty" + 
				"");
		assertEquals(59, authenticateEmploye.getId());
	}
	
	
	@Test
	public void testMettreAjourEmailByEmployeId() {
		Employe employe = new Employe("iheb", "lassoued", "iheb@gmailcom", true, Role.INGENIEUR);
		Employe addedEmp = iEmployeService.addOrUpdateEmploye(employe);
		Employe updatedEmploye = iEmployeService.mettreAjourEmailByEmployeId("bmahjoubi@gmailcom", addedEmp.getId());
		assertNotEquals(addedEmp.getEmail(), updatedEmploye.getEmail());
	}
	
	@Test
	public void testaddOrUpdateEmploye() {
		Employe employe = new Employe("Ahmed", "laaaa", "iheb@esprit.tn", true, Role.INGENIEUR);
		Employe addedEmp = iEmployeService.addOrUpdateEmploye(employe);
		assertEquals(employe.getNom(), addedEmp.getNom());
	}
	
	@Test
	public void TestmettreAjourEmailByEmployeId() {
		employeServiceImpl.mettreAjourEmailByEmployeId("iheb2@esprit.tn", 59);
		Employe employe = employeRepository.findById(59).get();

		assertEquals("iheb2@esprit.tn", employe.getEmail());

	}
	
	
	@Test
	public void testGetAllEmployes() {
		assertEquals(repo.countemp(), iEmployeService.getAllEmployes().size());
	}
	
	@Test
	public void testdeleteEmployeById() {
		Employe employe = new Employe("Aymen", "Bahri", "aymen@esprit.tn", true, Role.INGENIEUR);
		Employe addedEmp = iEmployeService.addOrUpdateEmploye(employe);
		assertTrue(iEmployeService.deleteEmployeById(addedEmp.getId()));
	}
	@Test
	public void testAffecterEmployeADepartement() {

		employeServiceImpl.affecterEmployeADepartement(59, 1);
		Employe employe = employeRepository.findById(59).get();
		int id1 = employe.getDepartements().get(0).getId();
		assertEquals(1, id1);
	}
	
	@Test
	public void TestdeleteAllContratJPQL() {
		employeServiceImpl.deleteAllContratJPQL();
		List<Contrat> contrat = (List<Contrat>) contratRepository.findAll();
		assertEquals(0, contrat.size());
	}
	
	
	

	
	
	@Test
	public void testGetEmployePrenomById() {
		Employe employe = new Employe("Sami", "Bahri", "raed.bahri@esprit.tn", true, Role.INGENIEUR);
		Employe addedEmp = iEmployeService.addOrUpdateEmploye(employe);
		assertNotNull(iEmployeService.getEmployePrenomById(addedEmp.getId()));

	}

}