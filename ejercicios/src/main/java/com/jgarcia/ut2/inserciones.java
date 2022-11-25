package com.jgarcia.ut2;

import java.sql.*;

public class inserciones {
    public static Connection getConnection() throws SQLException {
        return PoolConexiones.getConexion();
    }

    public static void main(String[] args)
    {
        String sql = "SELECT * FROM videojuegos";
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery())
        {
            //INSERCION moveToInserRow()
            //me posiciono en una fila donde puedo insertar nuevas filas
            rs.moveToInsertRow();
            rs.updateInt("videojuegoid",1);
            rs.updateString("titulo","Minecraft");
            //identifico el campo mediante el indice 3
            rs.updateDouble(3,19.99);
            rs.updateString("creador","Mojang");
            //para confirmar la insercion
            rs.insertRow();
            System.out.println("Registro insertado en la tabla videojuegos");

            //realizo dos inserciones mas
            rs.moveToInsertRow();
            rs.updateInt("videojuegoid",2);
            rs.updateString("titulo","League of Legends");
            //identifico el campo mediante el indice 3
            rs.updateDouble(3,169.99);
            rs.updateString("creador","Riot Games");
            rs.insertRow();
            System.out.println("Registro insertado en la tabla videojuegos");

            //realizo dos inserciones mas
            rs.moveToInsertRow();
            rs.updateInt("videojuegoid",3);
            rs.updateString("titulo","Valorant");
            //identifico el campo mediante el indice 3
            rs.updateDouble(3,59.99);
            rs.updateString("creador","Riot Games");
            rs.insertRow();
            System.out.println("Registro insertado en la tabla videojuegos");

            //nos movemos
            rs.beforeFirst();

            //LOS MUESTRO
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
