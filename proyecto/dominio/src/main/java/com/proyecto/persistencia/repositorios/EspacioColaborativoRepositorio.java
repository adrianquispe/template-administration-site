package com.proyecto.persistencia.repositorios;

import com.proyecto.model.espaciosColaborativos.EspacioColaborativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacioColaborativoRepositorio extends JpaRepository<EspacioColaborativo, Long> {
}
