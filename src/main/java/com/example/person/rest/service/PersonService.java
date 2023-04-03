package com.example.person.rest.service;

import com.example.person.rest.dto.PersonDto;
import com.example.person.rest.jpa.model.Person;
import com.example.person.rest.jpa.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public PersonDto getPersonDTO(Person person) {
    Person personInDb = personRepository.findById(person.getId()).orElseGet(() -> {
      Person newPerson = new Person(person.getName(), person.getAge());
      return personRepository.save(newPerson);
    });
    return new PersonDto(personInDb.getName(), personInDb.getAge());
  }

  public Person getPerson(List<Person> personList, boolean isOldest) {
    validatePersonList(personList);
    validatePersonObjects(personList);
    List<PersonDto> personDtoList = convertToDto(personList);
    PersonDto person = personDtoList.get(0);
    for (int i = 1; i < personList.size(); i++) {
      PersonDto currentPerson = personDtoList.get(i);
      if (isOldest) {
        person = getOldestPerson(person, currentPerson);
      } else {
        person = getYoungestPerson(person, currentPerson);
      }
    }
    return person;
  }

  private static PersonDto getYoungestPerson(PersonDto person, PersonDto currentPerson) {
    if (currentPerson.compareTo(person) < 0) {
      person = currentPerson;
    }
    return person;
  }

  private static PersonDto getOldestPerson(PersonDto person, PersonDto currentPerson) {
    if (currentPerson.compareTo(person) > 0) {
      person = currentPerson;
    }
    return person;
  }

  private List<PersonDto> convertToDto(List<Person> personList) {
    List<PersonDto> personDtoList = new ArrayList<>();
    for(Person person : personList) {
      PersonDto personDto = getPersonDTO(person);
      personDtoList.add(personDto);
    }
    return personDtoList;
  }

  private void validatePersonList(List<Person> personList) {
    if (personList.isEmpty()) {
      throw new IllegalArgumentException("List cannot be empty");
    }
  }

  private void validatePersonObjects(List<Person> personList) {
    for (Person person : personList) {
      if (person.getAge() == 0 || person.getName() == null) {
        throw new IllegalArgumentException("Person.java in the list has null age or name");
      }
    }
  }

}
