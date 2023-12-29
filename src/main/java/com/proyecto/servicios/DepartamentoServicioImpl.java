package com.proyecto.servicios;

import com.proyecto.model.departamento.Departamento;
import com.proyecto.model.departamento.TipoDepartamento;
import com.proyecto.persistencia.repositorios.DepartamentoRepositorio;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class DepartamentoServicioImpl implements DepartamentoServicio {

        @Autowired
        private DepartamentoRepositorio repositorio;

        @Autowired
        private EntityManager entityManager;

        @Override
        public List<Departamento> listaDepartamentos() {
            return repositorio.findAll();
        }

        @Override
        public Departamento guardarDepartamento(Departamento departamento) {
            return repositorio.save(departamento);
        }

        @Override
        public Departamento obtenerDepartamentoPorId(long id) {
            return repositorio.findById(id).get();
        }

        @Override
        public Departamento actualizarDepartamento(Departamento departamento) {
            return repositorio.save(departamento);
        }

        @Override
        public void eliminarDepartamento(Long id) {
            repositorio.deleteById(id);
        }


        public List<Departamento> departamentosSegunTipo(TipoDepartamento tipo){
            return entityManager
                    .createQuery("from Departamento where tipo = :tipo")
                    .setParameter("tipo", tipo)
                    .getResultList();
        }

        public List<Departamento> departamentosSinAsignar(){
            return repositorio.departamentosSinAsignar();
        }

    @Override
    public List<Departamento> departamentosSinAsignarYDeTipo(TipoDepartamento tipo) {
        return repositorio.departamentosSinAsignarYDeTipo(tipo);
    }

    @Override
    public List<Departamento> superDepartamentos(){
            return repositorio.superDepartamentos();
    }

  @Override
  public Page<Departamento> listaDepartamentos(Pageable pagina) {
    return repositorio.findAll(pagina);
  }

}
