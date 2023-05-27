package org.gfg.junitmock.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gfg.junitmock.demo.Repository.PersonRepository;
import org.gfg.junitmock.demo.controller.PersonController;
import org.gfg.junitmock.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PersonService implements PersonServiceInterface{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void addOnePerson(Person person) {
        Person person1 = personRepository.save(person);
        redisTemplate.opsForValue().set(person1.getEmail(), person1);
        redisTemplate.opsForList().leftPush(person1.getCountry(), person1);
        redisTemplate.opsForHash().putAll(person1.getName(), objectMapper.convertValue(person1, Map.class));
    }

    @Override
    public void addMultiplePersons(List<Person> person) {
        personRepository.saveAll(person);
    }

    @Override
    public Person getPersonDetails(String email) {

        Person person1 = (Person) redisTemplate.opsForValue().get(email);
        if(person1 != null){
            return person1;
        }
        Person person2=  personRepository.findByEmail(email);
        redisTemplate.opsForValue().set(person2.getEmail(),person2);
        return person2;
        // we will be putting data in to redis
    }

    @Override
    public List<Person> getMultiplePersons(String country , int start, int end) {
        List<Person> list = redisTemplate.opsForList().range(country,start, end );
        if(!list.isEmpty()){
            return list;
        }
        list = personRepository.findByCountry(country);
        for(Person p: list){

            //in list with country as key

            redisTemplate.opsForList().leftPush(p.getCountry(), p);
        }
        return list;
    }


    public Map<String, Person> getAllRedisPersonData(String name) {
        return redisTemplate.opsForHash().entries(name);
    }


    public List<Person> getPersonDataStartsWithA(){
        List<Person> result = new ArrayList<>();
        List<Person> personList = personRepository.findAll();

        for(Person p  : personList ){
            if(p.getName().startsWith("a")){
                result.add(p);
            }
        }
        return result;
    }

}


// save person data as a map
//key value pair