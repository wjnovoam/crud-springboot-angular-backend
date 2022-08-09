package com.wjnovoa.app.service;

import com.wjnovoa.app.dto.PersonDTO;
import com.wjnovoa.app.entity.Person;
import com.wjnovoa.app.exceptions.DocumetPersonExistsException;
import com.wjnovoa.app.exceptions.ResourceNotFountException;
import com.wjnovoa.app.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author William Johan Novoa Melendrez
 * @date 4/08/2022
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final ModelMapper modelMapper;

    private final PersonRepository personRepository;


    public PersonServiceImpl(ModelMapper modelMapper, PersonRepository personRepository) {
        this.modelMapper = modelMapper;
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> allPersons() {
        return personRepository.findAll();
    }

    @Override
    public PersonDTO addPerson(PersonDTO savePersonDTO) {

        if(existsDocumentPerson(savePersonDTO.getDocument())) {
            throw new DocumetPersonExistsException("documento",savePersonDTO.getDocument());
        }

        Person person = modelMapper.map(savePersonDTO, Person.class);
        Person newPerson = personRepository.save(person);

        return modelMapper.map(newPerson, PersonDTO.class);
    }

    @Override
    public PersonDTO updatePersonId(Long id, PersonDTO personDTO) {
        Person person = personRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFountException("Persona", "id", id));

        person.setName((personDTO.getName() != null)?personDTO.getName(): person.getName());
        person.setLastname((personDTO.getLastname() != null)?personDTO.getLastname(): person.getLastname());
        person.setDocument((personDTO.getDocument() != null)?personDTO.getDocument(): person.getDocument());
        person.setAge((personDTO.getAge() != 0)?personDTO.getAge(): person.getAge());
        person.setAlture((personDTO.getAlture() != 0.0)?personDTO.getAlture(): person.getAlture());
        person.setSize((personDTO.getSize() != null)?personDTO.getSize(): person.getSize());
        person.setWeight((personDTO.getWeight() != 0)?personDTO.getWeight(): person.getWeight());

        Person personUpdate = personRepository.save(person);

        return modelMapper.map(personUpdate, PersonDTO.class);
    }

    @Override
    public void deletePersonId(Long id) {
        Person person = personRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFountException("Persona", "id", id));

        personRepository.delete(person);
    }

    public boolean existsDocumentPerson(String document) {
        return personRepository.existsByDocument(document);
    }

}