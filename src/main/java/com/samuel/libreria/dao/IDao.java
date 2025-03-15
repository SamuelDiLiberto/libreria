package com.samuel.libreria.dao;

import java.util.Map;

import com.samuel.libreria.entity.GenericEntity;

public interface IDao<E extends GenericEntity> {
    Long create(E e);
    Map<Long, GenericEntity> read();
    void update(E e);
    void delete(Long id);
    E readById(Long id);
}
