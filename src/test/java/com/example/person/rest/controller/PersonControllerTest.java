package com.example.person.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.person.rest.configuration.SecurityConfiguration;
import com.example.person.rest.jpa.model.Person;
import com.example.person.rest.service.PersonService;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Import(SecurityConfiguration.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PersonService personService;

  @Test
  public void testGetPerson() throws Exception {
    List<Person> personList = Arrays.asList(
        new Person("Alice", 20),
        new Person("Bob", 30),
        new Person("Charlie", 25)
    );
    Person expectedPerson = new Person("Alice", 20);
    when(personService.getPerson(anyList(), anyBoolean())).thenReturn(expectedPerson);
    RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
            "/person").accept(
            MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        .content(new Gson().toJson(personList));
    MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
    assertThat(result.getModelAndView().getModel().get("person")).isEqualTo(expectedPerson);
  }
}
