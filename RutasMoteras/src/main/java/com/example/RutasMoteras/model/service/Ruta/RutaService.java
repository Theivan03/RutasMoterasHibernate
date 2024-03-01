package com.example.RutasMoteras.model.service.Ruta;

import com.example.RutasMoteras.model.entity.Ruta;
import com.example.RutasMoteras.model.repository.IRutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutaService implements IRutaService{
    @Autowired
    IRutaRepository rutaRepository;

    @Override
    public Ruta addRuta(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    @Override
    public List<Ruta> findAll() {
        return (List<Ruta>) rutaRepository.findAll();
    }

    @Override
    public Ruta findById(Long id) {
        return rutaRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        rutaRepository.deleteById(id);
    }

    @Override
    public Ruta update(Long id, Ruta ruta) throws Exception {
        Ruta ruta1 = rutaRepository.findById(id).orElseThrow(() -> new Exception("Error al actuelizar cocheMatricula: " + id));
        ruta.setId(ruta1.getId());
        return rutaRepository.save(ruta);
    }

    @Override
    public List<Ruta> filtrarPorTipoMoto(String id) {
        return rutaRepository.findRutaByTipoMoto(id);
    }

    @Override
    public List<Ruta> filtrarPorComunidad(String id) {
        return rutaRepository.findRutaByComunidadAutonoma(id);
    }

    @Override
    public List<Ruta> findRutaByFecha() {
        return rutaRepository.findRutaByFecha();
    }


}
