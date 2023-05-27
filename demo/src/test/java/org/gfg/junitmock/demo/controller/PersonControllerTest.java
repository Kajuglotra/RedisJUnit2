package org.gfg.junitmock.demo.controller;

import org.gfg.junitmock.demo.model.Person;
import org.gfg.junitmock.demo.org.gfg.junitmock.demo.service.PersonServiceTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PersonControllerTest {

    private PersonServiceTest personServiceTest = new PersonServiceTest();
    @Test
    public void testGetPerson(){
        String email = "user@email";
        Person p = personServiceTest.getPersonDetails(email);
        Assert.assertNull(p);
         email = "user1@gmail.com";
        Person p1 = personServiceTest.getPersonDetails(email);
        Assert.assertNotNull(p);

    }

    @Test
    public void getPersonDataWithA(){
        List<Person> p= personServiceTest.getPersonDataStartsWithA();
        Assert.assertNull(p);
    }


}
