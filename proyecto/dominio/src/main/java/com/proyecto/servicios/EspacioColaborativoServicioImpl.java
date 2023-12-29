package com.proyecto.servicios;

import com.proyecto.model.espaciosColaborativos.EspacioColaborativo;
import com.proyecto.persistencia.repositorios.EspacioColaborativoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspacioColaborativoServicioImpl implements EspacioColaborativoServicio{

    @Autowired
    private EspacioColaborativoRepositorio repositorio;

    @Override
    public EspacioColaborativo obtenerEspacioColaborativoPorId(Long id) {
        return repositorio.findById(id).get();
    }
    @Override
    public List<EspacioColaborativo> listarEspacioColaborativo(){
        return repositorio.findAll();
    }

    @Override
    public EspacioColaborativo guardarEspacioColaborativo(EspacioColaborativo espacioColaborativo) {
        return repositorio.save(espacioColaborativo);
    }

    @Override
    public EspacioColaborativo actualizarEspacioColaborativo(EspacioColaborativo espacioColaborativo) {
        return repositorio.save(espacioColaborativo);
    }

    @Override
    public void cancelarEspacioColaborativo(Long id) {
        repositorio.deleteById(id);
    }
}
