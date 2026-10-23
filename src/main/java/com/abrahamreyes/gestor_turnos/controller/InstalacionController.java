package com.abrahamreyes.gestor_turnos.controller;

import com.abrahamreyes.gestor_turnos.model.Instalacion;
import com.abrahamreyes.gestor_turnos.service.InstalacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping ("/instalaciones")
public class InstalacionController {
    
    private final InstalacionService service;

    public InstalacionController(InstalacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Instalacion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Instalacion buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping 
    public ResponseEntity<Instalacion> crear(@Valid @RequestBody Instalacion instalacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(instalacion));
    }

    @PutMapping("/{id}")
    public Instalacion actualizar (@PathVariable Long id, @Valid @RequestBody Instalacion instalacion) {
        return service.actualizar(id, instalacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
