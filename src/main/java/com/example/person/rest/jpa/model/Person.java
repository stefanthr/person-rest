package com.example.person.rest.jpa.model;

import com.example.person.rest.dto.PersonDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private int age;
  private PersonDto parentOne;
  private PersonDto parentTwo;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }


}
