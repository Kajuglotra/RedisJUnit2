package org.gfg.junitmock.demo.Repository;

import org.gfg.junitmock.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository <Person, Integer> {

    public Person findByEmail(String email);
    public List<Person> findByCountry(String country);
}
