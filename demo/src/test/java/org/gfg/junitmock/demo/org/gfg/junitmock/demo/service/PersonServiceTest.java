package org.gfg.junitmock.demo.org.gfg.junitmock.demo.service;

import org.gfg.junitmock.demo.model.Person;
import org.gfg.junitmock.demo.service.PersonServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceTest implements PersonServiceInterface {
    @Override
    public void addOnePerson(Person person) {

    }

    @Override
    public void addMultiplePersons(List<Person> person) {

    }

    @Override
    public Person getPersonDetails(String email) {
        if(email == "user@email"){
            return  null;
        }
        Person person1 = Person.builder().
                name("john").email(email).build();
        return person1;
    }

    @Override
    public List<Person> getMultiplePersons(String country, int start, int end) {
        return null;
    }

    @Override
    public List<Person> getPersonDataStartsWithA() {
        List<Person> result = new ArrayList<>();

        List<Person> personList = null;

        for(Person p  : personList ){
            if(p.getName().startsWith("a")){
                result.add(p);
            }
        }
        return result;
    }


}
