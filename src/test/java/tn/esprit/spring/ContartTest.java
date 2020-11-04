package tn.esprit.spring;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContartTest {
	@Autowired
	IEmployeService es;

	@Test
	public void contextLoads() {

		Contrat contrat = new Contrat(new Date(), "bbbb", 1);
		es.ajouterContrat(contrat);
	}

	@Test
	public void contextLoads1() {
		es.deleteContratById(3);
	}

	@Test
	public void contextLoads2() {
		es.deleteAllContratJPQL();
		
	}
}
