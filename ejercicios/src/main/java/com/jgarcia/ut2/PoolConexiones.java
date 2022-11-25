package com.jgarcia.ut2;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class PoolConexiones {
    private static BasicDataSource dataSource = null;

    public static DataSource getDataSource(){
        String jdbc = "jdbc:postgresql://localhost/jgarcia";
        if(dataSource == null){
            dataSource = new BasicDataSource();
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            dataSource.setUrl(jdbc);
            //TAMAÑO DEL POOL
            dataSource.setMinIdle(5);
            dataSource.setMaxIdle(10);
        }
        return dataSource;
    }

    public static Connection getConexion() throws SQLException{
        return getDataSource().getConnection();
    }
}
