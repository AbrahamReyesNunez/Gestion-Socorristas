package com.abrahamreyes.gestor_turnos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor 
@Table (name = "instalaciones")
public class Instalacion {
        
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "El nombre es obligatorio")
    @Size (max = 100)
    @Column (unique = true)
    private String nombre;

    @NotNull (message = "El tipo de instalacion es obligatorio")
    @Enumerated(EnumType.STRING)
    private TipoInstalacion tipo;

    @NotBlank (message = "La dirección es obligatoria")
    @Size (max = 200)
    private String direccion;

    @NotNull (message = "El numero de socorristas es obligatorio")
    @Min (value = 1, message = "El minimo de socorristas es 1")
    private Integer minimoSocorristas;
}   
