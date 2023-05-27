package org.gfg.junitmock.demo.controller;

import org.gfg.junitmock.demo.model.MyUser;
import org.gfg.junitmock.demo.model.Person;
import org.gfg.junitmock.demo.model.User;
import org.gfg.junitmock.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/create-one-Person")
    public void addOnePerson(@RequestBody Person person){
        personService.addOnePerson(person);
    }

    @PostMapping("/create-multiple-persons")
    public void addMultiplePersons(@RequestBody List<Person> person){
        personService.addMultiplePersons(person);
    }

    @GetMapping("/get-person")
    public Person getPerson(@RequestParam String email){
       return personService.getPersonDetails(email);
    }

    @GetMapping("/get-multiple-persons")
    public List<Person> getMultiplePerson(@RequestParam String country,
                                          @RequestParam(value = "start" , required = false, defaultValue = "0") int start,
                                          @RequestParam(value = "end" , required = false, defaultValue = "-1") int end
                                          ){
        return personService.getMultiplePersons(country, start, end);
    }
    @GetMapping("/get-redis-person-data")
    public Map<String, Person> getAllRedisPersonData(@RequestParam String name){
        return personService.getAllRedisPersonData(name);
    }
    @GetMapping("/get-person-data-with-a")
    public List<Person> getPersonDataWithA(){
        return personService.getPersonDataStartsWithA();
    }
    @GetMapping("/getData")
    public String getUser(){
        return "user";
    }

    @GetMapping("/testCode")
    public String getTestCode(){
        return "testing the code...";
    }

    @GetMapping("/developCode")
    public String getDevelopCode(){
        return "Developing the code...";
    }

    @GetMapping("/home")
    public String getHome(){
        return "Home...";
    }

    @GetMapping("/deployCode")
    public String getCodeDeployed(){
        return "Deploying code...";
    }

    @GetMapping("/userHome")
//    public String getHome(@RequestParam("name") String name){
    public String getHomes(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       MyUser user =  (MyUser) authentication.getPrincipal();
        return "At "+user.getUsername() + " Home.";
    }
}



