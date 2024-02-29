package com.example.RutasMoteras.model.repository;

import com.example.RutasMoteras.model.entity.Ruta;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Hidden
@Repository
public interface IRutaRepository extends CrudRepository<Ruta, Long> {

    @Query("select r from Ruta r where r.ComunidadAutonoma like ?1")
    List<Ruta> findRutaByComunidadAutonoma(String id);

    @Query("select r from Ruta r where r.TipoMoto like ?1")
    List<Ruta> findRutaByTipoMoto(String id);

}