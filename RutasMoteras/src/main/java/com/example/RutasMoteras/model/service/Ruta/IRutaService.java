package com.example.RutasMoteras.model.service.Ruta;

import com.example.RutasMoteras.model.entity.Ruta;

import java.util.List;

public interface IRutaService {
    Ruta addRuta(Ruta ruta);
    List<Ruta> findAll();
    Ruta findById(Long id);
    void delete(Long id);
    Ruta update(Long id, Ruta ruta) throws Exception;
    List<Ruta> filtrarPorTipoMoto(String id);
    List<Ruta> filtrarPorComunidad(String id);
    List<Ruta> findRutaByFecha();

}
