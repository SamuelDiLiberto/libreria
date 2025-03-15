package com.samuel.libreria.configuration;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.samuel.libreria.entity.Autore;

@Configuration
public class EntityContext {
    @Bean
    @Scope("prototype")
    public Autore autore(Map<String,String> mappa){
        Autore a = new Autore();
        a.fromMap(mappa);
        return a;
    }
}
