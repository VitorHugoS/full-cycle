package com.fullcycle.admin.catalogo.infraestructure;

import com.fullcycle.admin.catalogo.infraestructure.configuration.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;


@SpringBootApplication
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(WebConfiguration.class, args);
    System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");
    System.out.println("Hello world!");
  }
}