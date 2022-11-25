package com.jgarcia.ut2;

import java.sql.*;

public class transacciones {
    public static Connection getConnection() throws SQLException {
        return PoolConexiones.getConexion();
    }

    public static void main(String[] args) {
        //sql
        String altaVideojuego = "INSERT INTO videojuegos (videojuegoid, titulo, precio, creador) VALUES (?,?,?,?)";
        String altaVideojugador = "INSERT INTO videojugadores (videojugadorid, nombre, edad) VALUES (?,?,?)";
        String videojuego_videojugador = "INSERT INTO videojuego_videojugador (videojugador_id, videojuego_id) VALUES (?,?)";

        //declaro los objetos fuera del try-with-resources
        Connection con = null;
        PreparedStatement ps = null;

        //si va bien se ejecutará el try
        try
        {
            con = getConnection();
            //Hay que ponerlo en false porque es true por defecto
            con.setAutoCommit(false);

            //DOY VALORES AL PRIMER INSERT
            ps = con.prepareStatement(altaVideojuego);
            //Despues le ponemos 5 para crear un fallo
            ps.setInt(1,4);
            ps.setString(2,"Pokemon Escarlata");
            ps.setDouble(3,29.95);
            ps.setString(4,"Game Freak");
            ps.executeUpdate();
            ps.close();

            //DOY VALORES AL SEGUNDO INSERT
            ps = con.prepareStatement(altaVideojugador);
            ps.setInt(1,1);
            ps.setString(2,"manuel1234");
            ps.setInt(3,35);
            ps.executeUpdate();
            ps.close();

            //DOY VALORES AL TERCER INSERT
            ps = con.prepareStatement(videojuego_videojugador);
            ps.setInt(1,1);
            //Provocamos el fallo poniendo 5 en lugar de 4
            ps.setInt(2,5);
            ps.executeUpdate();
            ps.close();

            con.commit();
        }
        //si no va bien se ejecutará el catch
        catch (SQLException e)
        {
            try {
                System.out.println("rollback");
                //se ejecuta el rollback para deshacer el commit
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
