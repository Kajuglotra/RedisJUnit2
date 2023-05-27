package org.gfg.junitmock.demo.controller;

import org.gfg.junitmock.demo.model.Person;
import org.gfg.junitmock.demo.service.PersonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PrersonControllerTestWithMockito {
    @InjectMocks
    private PersonController personController;
    @Mock
    private PersonService personService;
    @Test
    public void getPersonDataWithA(){
        //case 1
        Mockito.when(personService.getPersonDataStartsWithA()).thenReturn(null);
        List<Person> p = personController.getPersonDataWithA();
        Assert.assertNull(p);

        List<Person> list1 = new ArrayList<>();
        Mockito.when(personService.getPersonDataStartsWithA()).thenReturn(list1);
        Assert.assertEquals(list1.size(), 0);

        List<Person> list2 = new ArrayList<>();
        list2.add(Person.builder().name("astha").build());
        list2.add(Person.builder().name("ayansh").build());
        Mockito.when(personService.getPersonDataStartsWithA()).thenReturn(list2);
        Assert.assertEquals(list1.size(), 2);

    }
    @Test
    public void testGetPersonDetails(){
        Mockito.when(personService.getPersonDetails("user@gmail")).thenReturn(Person.builder().build());
       Person p =  personService.getPersonDetails("user@gmail");
        Assert.assertEquals(p, null);
    }
}



// mockito --> i mock the value
// part i write what i expect ->
// mockito returning value and what i am expect is same
// test case will pass otherwise fail
//test driven dev

// refactoring -> changing the code by keeping the output same