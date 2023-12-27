package org.fsk.dockercompose.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fsk.dockercompose.entity.Person;
import org.fsk.dockercompose.repository.PersonRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    @Transactional
    public void loadData() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        File file = new ClassPathResource("mock-data.json").getFile();
        List<Person> persons = mapper.readValue(file, new TypeReference<>() {});
        personRepository.saveAll(persons);

    }


    @Cacheable(value = "PersonCache", key = "#city + '_' + #age")
    public List<Person> getPeopleByCityAndAge(String city, int age) {
        return personRepository.findAllByCountryAndAgeGreaterThanEqual(city, age);
    }

}
