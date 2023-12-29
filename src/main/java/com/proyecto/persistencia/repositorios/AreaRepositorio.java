package com.proyecto.persistencia.repositorios;

import com.proyecto.model.Area.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepositorio extends JpaRepository<Area, Long> {
}
