package tn.esprit.spring.services;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {
	
	@Autowired
	IEmployeService iEmployeService;
	
	@Test
	public void testMettreAjourEmailByEmployeId() {
		Employe employe = new Employe("iheb", "iheb2", "iheb1@esprit.tn", true, Role.INGENIEUR);
		Employe addedEmp = iEmployeService.addOrUpdateEmploye(employe);
		Employe updatedEmploye = iEmployeService.mettreAjourEmailByEmployeId("iheblaass@mail.com", addedEmp.getId());
		assertNotEquals(addedEmp.getEmail(), updatedEmploye.getEmail());
	}
	
	@Test
	public void testaddOrUpdateEmploye() {
		Employe employe = new Employe("Ahmed", "lacktyi2", "iheb2@esprit.tn", true, Role.INGENIEUR);
		Employe addedEmp = iEmployeService.addOrUpdateEmploye(employe);
		assertEquals(employe.getNom(), addedEmp.getNom());
	}
	
	@Test
	public void testGetAllEmployes() {
		assertEquals(7, iEmployeService.getAllEmployes().size());
	}
	
	@Test
	public void testdeleteEmployeById() {
		Employe employe = new Employe("Aymen", "lassoued", "iheb.lassoued@esprit.tn", true, Role.INGENIEUR);
		Employe addedEmp = iEmployeService.addOrUpdateEmploye(employe);
		assertTrue(iEmployeService.deleteEmployeById(addedEmp.getId()));
	}
	
	@Test
	public void testGetEmployePrenomById() {
		Employe employe = new Employe("Sami", "lassoued", "mo.lo@esprit.tn", true, Role.INGENIEUR);
		Employe addedEmp = iEmployeService.addOrUpdateEmploye(employe);
		assertNotNull(iEmployeService.getEmployePrenomById(addedEmp.getId()));

	}

}
