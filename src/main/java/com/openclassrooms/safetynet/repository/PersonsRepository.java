package com.openclassrooms.safetynet.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynet.model.Persons;

@Repository
public interface PersonsRepository
{
	public List<Persons> convertUrlToList();
	public void addPersons(Persons persons);
	public void updatePersons(Persons persons);
	public void deletePersons(String firstName, String lastName);
	

}
