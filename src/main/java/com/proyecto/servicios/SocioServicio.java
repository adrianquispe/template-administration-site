package com.proyecto.servicios;

import com.proyecto.model.socio.Socio;

import java.util.List;

public interface SocioServicio {

     public List<Socio> listaSocios();

     public Socio guardarSocio(Socio socio);

     public Socio obtenerSocioPorId(long id);

     public Socio actualizarSocio(Socio socio);

     public void eliminarSocio(Long id);

}
