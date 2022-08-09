package com.wjnovoa.app.repository;

import com.wjnovoa.app.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author William Johan Novoa Melendrez
 * @date 4/08/2022
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    Boolean existsByDocument(String document);
}