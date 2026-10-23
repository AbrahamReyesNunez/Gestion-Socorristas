package com.abrahamreyes.gestor_turnos.service;

import com.abrahamreyes.gestor_turnos.model.Instalacion;
import com.abrahamreyes.gestor_turnos.repository.InstalacionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service 
public class InstalacionService {
    
    private final InstalacionRepository repository;

    public InstalacionService(InstalacionRepository repository) {
        this.repository = repository;
    }

    public List<Instalacion> listar() {
        return repository.findAll();
    }

    public Instalacion buscar(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "no existe la instalacion con id "+ id));
    }

    public Instalacion crear (Instalacion instalacion) {
        if (repository.existsByNombre(instalacion.getNombre())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Ya existe una instalacion con nombre "+ instalacion.getNombre());
        }
        instalacion.setId(null);
        return repository.save(instalacion);
    }

    public Instalacion actualizar(Long id, Instalacion datos) {
        Instalacion existente = buscar(id);

        boolean cambiaNombre = !existente.getNombre().equals(datos.getNombre());
        if (cambiaNombre && repository.existsByNombre(datos.getNombre())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ya existe una instalacion con el nombre " + datos.getNombre());
        }
        existente.setNombre(datos.getNombre());
        existente.setTipo(datos.getTipo());
        existente.setDireccion(datos.getDireccion());
        existente.setMinimoSocorristas(datos.getMinimoSocorristas());

        return repository.save(existente);
    }

    public void eliminar (Long id) {
        repository.delete(buscar(id));
    }
}
