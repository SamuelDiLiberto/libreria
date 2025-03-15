package com.samuel.libreria.entity;

import lombok.Data;


// @Data perchè così mi crea un costruttore parametrizzato, getter e setter, toString senza che io li scriva
@Data
public abstract class GenericEntity implements IMappable{
    private Long id;
}
