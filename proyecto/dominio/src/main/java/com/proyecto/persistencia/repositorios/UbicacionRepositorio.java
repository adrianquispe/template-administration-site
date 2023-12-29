package com.proyecto.persistencia.repositorios;

import com.proyecto.model.socio.ubicacion.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepositorio extends JpaRepository<Ubicacion, Long>{

    @Query("SELECT u FROM Ubicacion u WHERE u.provincia = 'virtual'")
    Ubicacion ubicacionVirtual();
}
