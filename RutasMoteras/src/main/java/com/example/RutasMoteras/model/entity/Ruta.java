package com.example.RutasMoteras.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ruta")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 15)
    private String titulo;

    @Column
    private Date fecha_creacion;

    @Column(nullable = false, length = 15)
    private String descripcion;

    @Column(nullable = false, length = 20)
    private String ComunidadAutonoma;

    @Column(nullable = false, length = 20)
    private String TipoMoto;

}
