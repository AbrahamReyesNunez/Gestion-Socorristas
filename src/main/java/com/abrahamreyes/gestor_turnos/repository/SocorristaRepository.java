package com.abrahamreyes.gestor_turnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abrahamreyes.gestor_turnos.model.Socorrista;

public interface SocorristaRepository extends JpaRepository<Socorrista, Long> {

    boolean existsByDni(String dni);
}