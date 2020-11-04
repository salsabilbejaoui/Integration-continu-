package tn.esprit.spring.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.repository.DepartementRepository;

@RunWith(SpringRunner.class)
@SpringBootTest

public class DepartementServiceImplTest {
	@Autowired
	IDepartementService iDepartementService;
	
	@Autowired
	DepartementRepository repo ;
	
	@Test
	@Rollback(false)
	 public void testGetAllDepartements() {
		assertEquals(repo.countdep(), iDepartementService.getAllDepartements().size());
	}
}
