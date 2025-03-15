package com.samuel.libreria.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// @Data perchè così mi crea un costruttore parametrizzato, getter e setter, toString senza che io li scriva
// @ToString(callSuper=true) perchè così il super.toString() viene messo in questo toString() di Autore
@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
public class Autore extends GenericEntity{
  private String nome;
  private String cognome;
}
