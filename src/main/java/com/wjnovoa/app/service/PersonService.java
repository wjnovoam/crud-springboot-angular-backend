package com.wjnovoa.app.service;

import com.wjnovoa.app.dto.PersonDTO;
import com.wjnovoa.app.entity.Person;

import java.util.List;

/**
 * @author William Johan Novoa Melendrez
 * @date 4/08/2022
 */
public interface PersonService {
    List<Person> allPersons();
    PersonDTO addPerson(PersonDTO savePersonDTO);
    PersonDTO updatePersonId(Long id, PersonDTO personDTO);
    void deletePersonId(Long id);

    boolean existsDocumentPerson(String document);
}