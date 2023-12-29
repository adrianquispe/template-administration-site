package com.proyecto.controller;

import com.proyecto.configuracion.Configuracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/configuracion")
//@ComponentScan(basePackages = "com.proyecto")
public class ConfiguracionController {
  @Autowired
  private Configuracion configuracion;

  public ConfiguracionController(){
  }

  @GetMapping
  public Map<String, String> imprimirProperties(){
    return Map.of
        ("DB_PUERTO", configuracion.getPuerto(),
            "DB_HOST", configuracion.getHost(),
            "DB_NOMBRE", configuracion.getNombre(),
            "DB_USUARIO", configuracion.getUsuario(),
            "DB_PASSWORD", configuracion.getPassword(),
            "UPLOAD_DIR",configuracion.getUploadDir());
  }
}
