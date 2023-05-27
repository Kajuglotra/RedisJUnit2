package org.gfg.junitmock.demo.service;

import org.gfg.junitmock.demo.model.Person;

import java.util.List;

public interface PersonServiceInterface {

    void addOnePerson(Person person);

    public void addMultiplePersons(List<Person> person);

    Person getPersonDetails(String email);

    List<Person> getMultiplePersons(String country, int start, int end);

    public List<Person> getPersonDataStartsWithA();
}
