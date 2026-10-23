package com.abrahamreyes.gestor_turnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abrahamreyes.gestor_turnos.model.Instalacion;

public interface InstalacionRepository extends JpaRepository<Instalacion, Long> {

    boolean existsByNombre(String nombre);
}