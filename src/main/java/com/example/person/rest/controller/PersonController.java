package com.example.person.rest.controller;

import com.example.person.rest.jpa.model.Person;
import com.example.person.rest.service.PersonService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PersonController {

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping("/person")
  public Person getPerson(@RequestBody List<Person> personList, @RequestParam(required = false, defaultValue = "false") boolean isOldest) {
    return personService.getPerson(personList, isOldest);
  }

}
