package com.samuel.libreria.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.samuel.libreria.dao.DaoAutore;
import com.samuel.libreria.entity.Autore;

// @Service perchè questa è una classe di servizio

@Service
public class ServiceAutore extends GenericService<Autore, DaoAutore>{

  @Override
  public Autore construct(Map<String, String> entita) {
    return getContext().getBean(Autore.class, entita);
  }
}
