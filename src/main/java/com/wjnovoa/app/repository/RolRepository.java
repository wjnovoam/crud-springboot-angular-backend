package com.wjnovoa.app.repository;

import com.wjnovoa.app.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author William Johan Novoa Melendrez
 * @date 4/08/2022
 */
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByName(String name);
}