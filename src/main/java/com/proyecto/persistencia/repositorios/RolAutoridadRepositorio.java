package com.proyecto.persistencia.repositorios;

import com.proyecto.model.departamento.autoridadDepartamento.RolAutoridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolAutoridadRepositorio extends JpaRepository<RolAutoridad, Long> {
}
