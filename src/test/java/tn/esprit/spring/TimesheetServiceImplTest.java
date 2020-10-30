package tn.esprit.spring;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.DepartementServiceImpl;
import tn.esprit.spring.services.TimesheetServiceImpl;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetServiceImplTest {

	@Autowired
	private TimesheetServiceImpl timesheetServiceImpl;
	@Autowired
	private DepartementServiceImpl departementServiceImpl;
	@Autowired
	private MissionRepository  missionRepository; 
	@Autowired
	private DepartementRepository departementRepository;
	@Autowired
	private EmployeRepository employeRepository;
	

	@Test
	public void testajouterTimesheet(){
		Mission	m1=missionRepository.findById(9).get();
		Employe e1=employeRepository.findById(26).get();
		Date dateDebut=new Date();
		Date dateFin=new Date();
		Timesheet T1=timesheetServiceImpl.ajouterTimesheet(m1.getId(), e1.getId(), dateDebut, dateFin);
		assertEquals(T1.getTimesheetPK().getIdMission(),m1.getId());
		assertEquals(T1.getTimesheetPK().getIdEmploye(),e1.getId());
		assertEquals(T1.getTimesheetPK().getDateDebut(),dateDebut);
		assertEquals(T1.getTimesheetPK().getDateFin(),dateFin);
	}
	
	@Test
	public void validerTimesheet(){
		
		Mission	m1=missionRepository.findById(9).get(); 
		Employe validateurId=employeRepository.findById(13).get();
		Employe e1=employeRepository.findById(26).get();
		Date dateDebut=new Date();
		Date dateFin=new Date();
		Timesheet T1=timesheetServiceImpl.validerTimesheet(m1.getId(), e1.getId(), dateDebut, dateFin, validateurId.getId());
		System.out.println("T1.getTimesheetPK().getIdMission()"+T1.getTimesheetPK().getIdMission());
		System.out.println("m1.getId()"+m1.getId());
		
		
		assertEquals(T1.getTimesheetPK().getIdMission(),m1.getId());
		assertEquals(T1.getTimesheetPK().getIdEmploye(),e1.getId());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(dateFormat.format(T1.getTimesheetPK().getDateDebut()),dateFormat.format(dateDebut));
		assertEquals(dateFormat.format(T1.getTimesheetPK().getDateFin()),dateFormat.format(dateFin));
		
	}

}