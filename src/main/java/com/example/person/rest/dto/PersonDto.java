package com.example.person.rest.dto;

import com.example.person.rest.jpa.model.Person;

public class PersonDto extends Person implements Comparable<PersonDto>{

  public PersonDto(String name, int age) {
    super(name, age);
  }

  public PersonDto getPerson(Person person) {
    return new PersonDto(person.getName(), person.getAge());
  }

  @Override
  public int compareTo(PersonDto otherPerson) {
    int compareAge = Integer.compare(this.getAge(), otherPerson.getAge());
    if (compareAge != 0) {
      return compareAge;
    }
    return this.getName().compareTo(otherPerson.getName());
  }

}
