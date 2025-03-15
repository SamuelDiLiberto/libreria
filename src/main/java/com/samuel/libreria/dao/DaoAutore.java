package com.samuel.libreria.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.samuel.libreria.database.DatabaseMySql;
import com.samuel.libreria.entity.Autore;
import com.samuel.libreria.entity.GenericEntity;

import lombok.Data;

// @Repository perchè è l'annotazione dei DAO
// @Data perchè così mi crea un costruttore parametrizzato, getter e setter, toString senza che io li scriva

@Repository
@Data
public class DaoAutore implements IDao<Autore>{

  // final perchè c'è @Data. Se non c'era bastava mette AutoWired. cmq fa Dependency Injection
  private final DatabaseMySql databaseMySql;

  // content perchè è un contenitore/gestore di bean
  private final ApplicationContext context;

  @Override
  public Long create(Autore e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public Map<Long, GenericEntity> read() {
    String query = "SELECT * FROM autori";
    Map<Long, Map<String, String>> checkpoint = databaseMySql.executeDQL(query);
    Map<Long, GenericEntity> ris = new HashMap<>();
    GenericEntity e = null;
    for (Map<String,String> entita : checkpoint.values()) {

      // Inversion Of Control di Spring tramite bean
      e = context.getBean(Autore.class, entita);
      if(e != null){
        ris.put(e.getId(), e);
      }
    }
    return ris;
  }

  @Override
  public void update(Autore e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public Autore readById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'readById'");
  }

}
