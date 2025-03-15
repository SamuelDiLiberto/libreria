package com.samuel.libreria.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

//@Service perchè il database è un servizio

@Service
public class DatabaseMySql implements IDatabase{
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String url;
    private Connection connection;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;


    public DatabaseMySql(){}


    @PostConstruct
    private void openConnection(){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void close(PreparedStatement ps,ResultSet rs){
        try {
            if(ps != null)
                ps.close();
            if(rs != null)
                rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Long executeDML(String query, String... parametri) {
        Long id = -1L;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String generatedColumns[] = {"id"};
            ps = connection.prepareStatement(query,generatedColumns);
            for(int i = 0; i < parametri.length; i++){
                ps.setString(i+1, parametri[i]);
            }
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getLong(1);
            }
        }catch(Exception e){
            System.out.println("Errore esecuzione query: " + e.getMessage());
            e.printStackTrace();
            return -2L;
        }finally{
            close(ps, rs);
        }
        return id;
    }

    @Override
    public Map<Long, Map<String, String>> executeDQL(String query, String... parametri) {
        Map<Long, Map<String, String>> result = new HashMap<>();
       PreparedStatement ps = null;
       ResultSet rs = null;
         try{
              ps = connection.prepareStatement(query);
              for(int i = 0; i < parametri.length; i++){
                ps.setString(i+1, parametri[i]);
              }
              rs = ps.executeQuery();
              Map<String, String> mappaProprietà;
              int columnCount = rs.getMetaData().getColumnCount();
              while(rs.next()){
                mappaProprietà = new HashMap<>();
                for(int i = 1; i <= columnCount; i++){
                    mappaProprietà.put(rs.getMetaData().getColumnName(i),
                     rs.getString(i));
                }
                result.put(rs.getLong("id"), mappaProprietà);
              }
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                close(ps, rs);
            }
        return result;
    }
}
