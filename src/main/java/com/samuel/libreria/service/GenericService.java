package com.samuel.libreria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.samuel.libreria.dao.IDao;
import com.samuel.libreria.entity.GenericEntity;

import lombok.Getter;

//@Getter perchè recupero tutti i metodi get

@Getter
public abstract class GenericService <E extends GenericEntity, D extends IDao<E>>{

    // @Autowired perchè non ho messo @Data. se mettevo @Data al posto di @Autowired dovevo mettere le variabili final

    @Autowired
    private D dao;
    @Autowired
    private ApplicationContext context;

    public List<E> findAll(){
        Map<Long,GenericEntity> result = dao.read();
        List<E> lista = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            lista.add((E)e);
        }
        return lista;
    }

    public E findById(Long id){
        return dao.readById(id);
    }

    public abstract E construct(Map<String,String> entita);

    public void update(Map<String,String> entita){
        E entity = construct(entita);
        dao.update(entity);
    }

    public void delete(Long id){
        dao.delete(id);
    }

}
