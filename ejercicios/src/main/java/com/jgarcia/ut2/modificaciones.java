package com.jgarcia.ut2;

import java.sql.*;

public class modificaciones {
    public static Connection getConnection() throws SQLException {
        return PoolConexiones.getConexion();
    }

    public static void main(String[] args)
    {
        String sql = "SELECT * FROM videojuegos";
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery())
        {
            //me pongo en la ultima posicion
            rs.last();
            //modificamos
            //cambio el precio
            rs.updateDouble("precio",29.99);
            //cambio el creador
            rs.updateString("creador","Rito Davelopements");
            rs.updateRow(); //ejecutamos la actualizacion de datos
            System.out.println("Modificacion del videojuego realizada");

            //no movemos a la fila 2
            rs.absolute(2);
            System.out.println("\nEstamos en la fila " + rs.getRow());
            //cambio el precio
            rs.updateDouble("precio",77.77);
            rs.updateRow();
            System.out.println("Modificacion del videojuego realizada");

            //me muevo al primero
            rs.first();
            System.out.println("\nAhora en la fila " + rs.getRow());
            rs.updateString("creador","Studio Santa Maria");
            rs.updateRow();
            System.out.println("Modificacion del videojuego realizada");

            //me muevo con relative
            System.out.println("\nAntes de moverme mediante relative() estoy en " + rs.getRow());
            //me muevo dos posiciones hacia delante respecto a la fila actual
            rs.relative(2);
            System.out.println("Después de moverme mediante relative() estoy en " + rs.getRow());
            rs.updateString("titulo","Legend of Zelda");
            rs.updateRow();
            System.out.println("Modificacion del videojuego realizada");
            System.out.println("A continuacion se mostraran los videojuegos");

            //me muevo al principio
            rs.beforeFirst();
            while(rs.next())
            {
                System.out.println("\nId: " + rs.getInt(1));
                System.out.println("Titulo: " + rs.getString(2));
                System.out.println("Precio: " + rs.getDouble(3));
                System.out.println("Autor: " + rs.getString(4));
                System.out.println("\n-------------------------------------------");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
