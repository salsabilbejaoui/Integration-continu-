package tn.esprit.spring;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IEmployeService;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TimesheetApplicationTests {
@Autowired 
IEmployeService employer;


	@Test
   public void contextLoads() {
		Employe u=new Employe(1,"bejaoui", "SALSA","n", "FFFF",true, Role.CHEF_DEPARTEMENT);
		employer.addEmploye(u);
	}

}
