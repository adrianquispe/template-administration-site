package com.proyecto.persistencia.repositorios;

import com.proyecto.model.socio.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocioRepositorio extends JpaRepository<Socio, Long> {
}
