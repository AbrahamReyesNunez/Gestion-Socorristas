package com.abrahamreyes.gestor_turnos.service;

import com.abrahamreyes.gestor_turnos.model.Socorrista;
import com.abrahamreyes.gestor_turnos.repository.SocorristaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SocorristaService {

    private final SocorristaRepository repository;

    public SocorristaService(SocorristaRepository repository) {
        this.repository = repository;
    }

    public List<Socorrista> listar() {
        return repository.findAll();
    }

    public Socorrista buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe el socorrista con id " + id));
    }

    public Socorrista crear(Socorrista socorrista) {
        if (repository.existsByDni(socorrista.getDni())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ya existe un socorrista con DNI " + socorrista.getDni());
        }
        socorrista.setId(null);
        return repository.save(socorrista);
    }

    public Socorrista actualizar(Long id, Socorrista datos) {
        Socorrista existente = buscar(id);

        boolean cambiaDni = !existente.getDni().equals(datos.getDni());
        if (cambiaDni && repository.existsByDni(datos.getDni())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ya existe un socorrista con DNI " + datos.getDni());
        }

        existente.setNombre(datos.getNombre());
        existente.setApellidos(datos.getApellidos());
        existente.setDni(datos.getDni());
        existente.setTelefono(datos.getTelefono());
        existente.setFechaCaducidadTitulo(datos.getFechaCaducidadTitulo());
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }
}