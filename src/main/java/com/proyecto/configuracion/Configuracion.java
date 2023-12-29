package com.proyecto.configuracion;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties("configuracion")
public class Configuracion {

  @Value("${DB_PUERTO}")
  @Setter @Getter
  private String puerto;

  @Value("${DB_HOST}")
  @Setter @Getter
  private String host;

  @Value("${DB_NOMBRE}")
  @Setter @Getter
  private String nombre;

  @Value("${DB_USUARIO}")
  @Setter @Getter
  private String usuario;

  @Value("${DB_PASSWORD}")
  @Setter @Getter
  private String password;

  @Value("${upload.path}")
  @Setter @Getter
  private String uploadDir;

  public Configuracion(){}
  public Configuracion(String puerto, String host, String nombre, String usuario, String password, String uploadDir) {
    this.puerto = puerto;
    this.host = host;
    this.nombre = nombre;
    this.usuario = usuario;
    this.password = password;
    this.uploadDir = uploadDir;
  }
}
