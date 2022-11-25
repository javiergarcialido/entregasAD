package com.jgarcia.ut2;

import java.sql.*;

public class creaBBDD {
    public static void main( String[] args )
    {
        String sql;
        try(Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "root", "root");
            Statement sentencia = conexion.createStatement()
        )
        {
            //CREACION BASE DE DATOS
            System.out.println("Conexión establecida con la Base de Datos");
            sql = "DROP DATABASE IF EXISTS jgarcia; CREATE DATABASE jgarcia"; //para crear la base de datos desde jdbc hay que conectarse a
            //localhost/postgres
            sentencia.executeUpdate(sql); //ejecuto la sentencia y creo la BD
            System.out.println("Base de Datos 'jgarcia' creada");

            //AHORA HAY QUE CONECTARSE A LA BASE DE DATOS CREADA, libros
            try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/jgarcia", "root", "root");
                Statement st = conn.createStatement()
            )
            {
                //CREACION TABLAS
                System.out.println("Conexion establecida con la Base de Datos samuel");
                sql =   "CREATE TABLE videojuegos(" +
                        "VIDEOJUEGOID int NOT NULL, " +
                        "TITULO char(40) NOT NULL, "+
                        "PRECIO numeric NOT NULL, "+
                        "CREADOR char(40) NOT NULL, "+
                        "CONSTRAINT PK_VIDEOJUEGOID PRIMARY KEY (VIDEOJUEGOID));";
                st.executeUpdate(sql);
                System.out.println("Tabla videojuegos creada");

                sql =   "CREATE TABLE videojugadores(" +
                        "VIDEOJUGADORID int NOT NULL,"+
                        "NOMBRE char(40) NOT NULL,"+
                        "EDAD int NOT NULL, " +
                        "CONSTRAINT PK_VIDEOJUGADOR PRIMARY KEY (VIDEOJUGADORID));";

                st.executeUpdate(sql);
                System.out.println("Tabla videojugadores creada");

                sql =   "CREATE TABLE videojuego_videojugador("+
                        "VIDEOJUGADOR_ID int NOT NULL,"+
                        "VIDEOJUEGO_ID int NOT NULL,"+
                        "CONSTRAINT VIDEOJUGADOR_VIDEOJUEGO FOREIGN KEY (VIDEOJUGADOR_ID) REFERENCES videojugadores(VIDEOJUGADORID),"+
                        "CONSTRAINT VIDEOJUEGO_VIDEOJUGADOR FOREIGN KEY (VIDEOJUEGO_ID) REFERENCES videojuegos(VIDEOJUEGOID));";


                st.executeUpdate(sql);
                System.out.println("Tabla videojuego_videojugador creada");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
