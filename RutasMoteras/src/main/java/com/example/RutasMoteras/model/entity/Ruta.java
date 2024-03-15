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

    @Column(nullable = false, length = 20)
    private String titulo;

    @Column
    private Date fecha_creacion;

    @Column(nullable = false, length = 250)
    private String descripcion;

    @Column(nullable = false, length = 20, name = "comunidad_autonoma")
    private String ComunidadAutonoma;

    @Column(nullable = false, length = 20, name = "tipo_moto")
    private String TipoMoto;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(name = "imageurl")
    private String imageURL;

}
