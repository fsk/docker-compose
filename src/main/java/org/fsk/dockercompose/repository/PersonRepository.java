package org.fsk.dockercompose.repository;

import org.fsk.dockercompose.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByCountryAndAgeGreaterThanEqual(String country, int age);

}
