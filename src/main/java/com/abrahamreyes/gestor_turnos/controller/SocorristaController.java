package com.abrahamreyes.gestor_turnos.controller;

import com.abrahamreyes.gestor_turnos.model.Socorrista;
import com.abrahamreyes.gestor_turnos.service.SocorristaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socorristas")
public class SocorristaController {

    private final SocorristaService service;

    public SocorristaController(SocorristaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Socorrista> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Socorrista buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<Socorrista> crear(@Valid @RequestBody Socorrista socorrista) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(socorrista));
    }

    @PutMapping("/{id}")
    public Socorrista actualizar(@PathVariable Long id, @Valid @RequestBody Socorrista socorrista) {
        return service.actualizar(id, socorrista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}