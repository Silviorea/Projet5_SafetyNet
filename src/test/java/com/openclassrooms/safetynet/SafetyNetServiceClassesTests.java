package com.openclassrooms.safetynet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetynet.controler.AccessToDataController;
import com.openclassrooms.safetynet.model.Firestations;
import com.openclassrooms.safetynet.model.MedicalRecords;
import com.openclassrooms.safetynet.model.Persons;
import com.openclassrooms.safetynet.service.AccessToDataService;
import com.openclassrooms.safetynet.service.FirestationsService;
import com.openclassrooms.safetynet.service.MedicalRecordsService;
import com.openclassrooms.safetynet.service.PersonsService;

@SpringBootTest
class SafetyNetServiceClassesTests
{

	@Autowired
	PersonsService personsService;
	@Autowired
	FirestationsService firestationsService;
	@Autowired
	MedicalRecordsService medicalRecordsService;
	@Autowired
	AccessToDataService accessToDataService;

	List<Persons> personsListTest = new ArrayList<>();
	List<Firestations> firestationsListTest = new ArrayList<>();
	List<MedicalRecords> medicalRecordsListTest = new ArrayList<>();
	Persons personsTest = new Persons("Silvio", "REA", "Test address", "city", "zip", "phone", "email");
	Firestations firestationsTest = new Firestations("Test address", "Test station");
	String[] arrayMedications = { "medi1", "medi2" };
	String[] arrayAllergies = { "allerg1", "allerg2" };
	MedicalRecords medicalRecordsTest = new MedicalRecords("Silvio", "REA", "26/09/1986", arrayMedications,
			arrayAllergies);
	
	
	

	@Test
	void contextLoads()
	{
	}

	//////////////// PersonsService TESTS ////////////////////

	@Test
	public void testIfConvertUrlToPersonsListWorks()
	{
		personsListTest = personsService.convertUrlToList();
		assertNotNull(personsListTest);
	}

	@Test
	public void testAddPersonsMethod()
	{
		personsService.addPersons(personsTest);
		assertTrue(personsService.getPersonsList().contains(personsTest));
	}

	@Test
	public void testUpdatePersonsMethod()
	{
		personsListTest.add(personsTest);
		personsService.setPersonsList(personsListTest);
		personsService.updatePersons(new Persons("Silvio", "REA", "TEST", "TEST", "TEST", "TEST", "TEST"));
		assertTrue(personsTest.getAddress().equals("TEST"));
	}

	@Test
	public void testDeletePersonsMethod()
	{
		personsListTest.add(personsTest);
		personsService.setPersonsList(personsListTest);
		personsService.deletePersons("Silvio", "REA");
		assertFalse(personsService.getPersonsList().contains(personsTest));
	}

	//////////////// FirestationsService TESTS ////////////////////

	@Test
	public void testIfConvertUrlToFirestationsListWorks()
	{
		firestationsListTest = firestationsService.convertUrlToList();
		assertNotNull(firestationsListTest);
	}

	@Test
	public void testAddFirestationsMethod()
	{
		firestationsService.addFirestation(firestationsTest);
		assertTrue(firestationsService.getFirestationsList().contains(firestationsTest));
	}

	@Test
	public void testUpdateFirestationsMethod()
	{
		firestationsListTest.add(firestationsTest);
		firestationsService.setFirestationsList(firestationsListTest);
		;
		firestationsService.updateFirestation("Test address", "Test station", "55");
		;
		assertTrue(firestationsTest.getStation().equals("55"));
	}

	@Test
	public void testDeleteFirestationsMethod()
	{
		firestationsListTest.add(firestationsTest);
		firestationsService.setFirestationsList(firestationsListTest);
		firestationsService.deleteFirestation("Test address", "Test station");
		assertFalse(firestationsService.getFirestationsList().contains(firestationsTest));
	}

	//////////////// MedicalRecordsService TESTS ////////////////////

	@Test
	public void testIfConvertUrlToMedicalRecordsListWorks()
	{
		medicalRecordsListTest = medicalRecordsService.convertUrlToList();
		assertNotNull(medicalRecordsListTest);
	}

	@Test
	public void testAddMedicalRecordsMethod()
	{
		medicalRecordsService.addMedicalRecords(medicalRecordsTest);
		assertTrue(medicalRecordsService.getMedicalRecordsList().contains(medicalRecordsTest));
	}

	@Test
	public void testDeleteMedicalRecordsMethod()
	{
		medicalRecordsListTest.add(medicalRecordsTest);
		medicalRecordsService.setMedicalRecordsList(medicalRecordsListTest);
		medicalRecordsService.deleteMedicalRecords("Silvio", "REA");
		assertFalse(medicalRecordsService.getMedicalRecordsList().contains(medicalRecordsTest));
	}

	@Test
	public void testUpdateMedicalRecordsMethod()
	{
		medicalRecordsListTest.add(medicalRecordsTest);
		medicalRecordsService.setMedicalRecordsList(medicalRecordsListTest);
		String[] newMedications = { "new medication 1", "new medication 2" };
		String[] newAllergies = { "new allergie 1", "new allergie 2" };
		medicalRecordsService
				.updateMedicalRecords(new MedicalRecords("Silvio", "REA", "26/09/1986", newMedications, newAllergies));
		assertTrue(medicalRecordsTest.getAllergies().equals(newAllergies)
				&& medicalRecordsTest.getMedications().equals(newMedications));
	}

	
	
	//////////////// AccessToDataService TESTS ////////////////////

	
	@Test
	public void testGetFirestationsFromPersonsMethod()
	{
		personsListTest.add(personsTest);
		personsService.setPersonsList(personsListTest);
		firestationsListTest.add(firestationsTest);
		firestationsService.setFirestationsList(firestationsListTest);
		medicalRecordsListTest.add(medicalRecordsTest);
		medicalRecordsService.setMedicalRecordsList(medicalRecordsListTest);
		
		String result = accessToDataService.getFirestationsFromPersons("Test station").toString();
		assertEquals("[First Name : Silvio, Last Name : REA, Address : Test address, Phone Number : phone, Children : 0, Adults : 1]"
				, result);
	}
	
	@Test
	public void testGetChildrenMethod()
	{
		personsListTest.add(personsTest);
		personsListTest.add(new Persons("Valentina", "REA", "Test address", "city", "zip", "phone", "email"));
		personsService.setPersonsList(personsListTest);
		medicalRecordsListTest.add(medicalRecordsTest);
		medicalRecordsListTest.add(new MedicalRecords("Valentina", "REA", "10/10/2017", arrayMedications,
				arrayAllergies));
		medicalRecordsService.setMedicalRecordsList(medicalRecordsListTest);
		
		String result = accessToDataService.getChildren("Test address").toString();
		
		assertEquals("[First Name : Valentina, Last Name : REA, Age : 4, Other family members : [Silvio, Valentina]]"
				, result);
	}
	
	@Test
	public void testGetPhoneNumbersMethod()
	{
		personsListTest.add(personsTest);
		personsService.setPersonsList(personsListTest);
		firestationsListTest.add(firestationsTest);
		firestationsService.setFirestationsList(firestationsListTest);
		
		String result = accessToDataService.getPhoneNumbers("Test station").toString();

		assertEquals("[phone]", result);
		
	}
	
	
	
	
	@Test
	public void testGetPersonsFromAddressMethod()
	{
		personsListTest.add(personsTest);
		personsService.setPersonsList(personsListTest);
		firestationsListTest.add(firestationsTest);
		firestationsService.setFirestationsList(firestationsListTest);
		medicalRecordsListTest.add(medicalRecordsTest);
		medicalRecordsService.setMedicalRecordsList(medicalRecordsListTest);
		
		
		String result = accessToDataService.getPersonsFromAddress("Test address").toString();
		
		assertEquals("[First Name : Silvio, Last Name : REA, Phone Number : phone, Age : 35, "
				+ "Allergies : [allerg1, allerg2], Medications : [medi1, medi2], Station Number : Test station]" 
				, result);
	}
	
	
	@Test
	public void testGetPersonsFromStationMethod()
	{
		personsListTest.add(personsTest);
		personsService.setPersonsList(personsListTest);
		firestationsListTest.add(firestationsTest);
		firestationsService.setFirestationsList(firestationsListTest);
		medicalRecordsListTest.add(medicalRecordsTest);
		medicalRecordsService.setMedicalRecordsList(medicalRecordsListTest);
		
		String result = accessToDataService.getPersonsFromStation("Test station").toString();
		assertEquals("[Address : Test address, First Name : Silvio, Last Name : REA, "
				+ "Allergies : [allerg1, allerg2], Medications : [medi1, medi2], "
				+ "Age : 35, Phone Number : phone]", result);
		
	}
	
	@Test
	public void testGetPersonsMethod()
	{
		personsListTest.add(personsTest);
		personsService.setPersonsList(personsListTest);
		medicalRecordsListTest.add(medicalRecordsTest);
		medicalRecordsService.setMedicalRecordsList(medicalRecordsListTest);
		
		String result = accessToDataService.getPersons().toString();

		assertEquals("[First Name : Silvio, Last Name : REA, Mail : email, Age : 35, Allergies : [allerg1, allerg2], Medications : [medi1, medi2]]", result);
	}
	
	@Test
	public void testGetEmail()
	{
		personsListTest.add(personsTest);
		personsService.setPersonsList(personsListTest);
		
		String result = accessToDataService.getEmail().toString();
		
		assertEquals("[email]", result);
	}
	
	
}
