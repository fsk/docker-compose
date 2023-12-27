package org.fsk.dockercompose.controller;

import lombok.RequiredArgsConstructor;
import org.fsk.dockercompose.entity.Person;
import org.fsk.dockercompose.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequestMapping("/person")
@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/load")
    public void loadDataFromJsonFileToDb() throws IOException {
        personService.loadData();
    }

    @GetMapping("/getFromDb/{city}/{age}")
    public List<Person> getFromDb(@PathVariable("city") String city, @PathVariable("age") int age) {
        return personService.getPeopleByCityAndAge(city, age);
    }

}
