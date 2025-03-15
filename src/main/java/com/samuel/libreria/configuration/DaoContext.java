package com.samuel.libreria.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.samuel.libreria.database.DatabaseMySql;
import com.samuel.libreria.database.IDatabase;

@Configuration
public class DaoContext {
    @Bean
    @Qualifier("databaseMYSQL")
    public IDatabase databaseMySQL(){
        return new DatabaseMySql();
    }
}
