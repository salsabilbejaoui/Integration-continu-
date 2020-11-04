package tn.esprit.spring;

import org.junit.Assert;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.TimesheetServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MissionApplicationTests {
	@Autowired 
	TimesheetServiceImpl timesheetservice ;
	@Test
	public void ajouterMissionTest()  {
		Mission ms= new Mission(3,"livraison", "vers gabes");
		timesheetservice.ajouterMission(ms);
		
	}
	@Test
public void	affecterMissionADepartementTest()
{

int iddep=timesheetservice.affecterMissionADepartement(1,1);
Assert.assertEquals(1 ,iddep);
}
	
	@Test 
	public void findAllMissionByEmployeJPQLTest()  {
	int nbremission=timesheetservice.findAllMissionByEmployeJPQL(1).size();
	Assert.assertEquals(2,nbremission);
	}
	@Test
	public void getAllEmployeByMission()
	{
		int nbreemploye=timesheetservice.getAllEmployeByMission(1).size();
		Assert.assertEquals(2,nbreemploye);
	}
}
