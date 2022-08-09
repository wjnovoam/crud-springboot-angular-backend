package com.wjnovoa.app.controller;

import com.wjnovoa.app.dto.PersonDTO;
import com.wjnovoa.app.entity.Person;
import com.wjnovoa.app.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author William Johan Novoa Melendrez
 * @date 4/08/2022
 */

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> allPersons(){
        return ResponseEntity.ok(personService.allPersons());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO savePersonDTO){
        return ResponseEntity.ok(personService.addPerson(savePersonDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PersonDTO> updatePerson(@Valid @PathVariable(name = "id") Long id,
                                                  @RequestBody PersonDTO personDTO){
        return ResponseEntity.ok(personService.updatePersonId(id, personDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map> updatePerson(@Valid @PathVariable(name = "id") Long id){
        Map<String, Object> responseMap = new HashMap<>();
        personService.deletePersonId(id);
        responseMap.put("message", "Persona eliminada con exito");
        responseMap.put("status", 200);
        return ResponseEntity.ok(responseMap);
    }
}