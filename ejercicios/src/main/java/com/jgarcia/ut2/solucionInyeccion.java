package com.jgarcia.ut2;

import java.sql.*;
import java.util.Scanner;

public class solucionInyeccion {
    public static Connection getConnection() throws SQLException {
        return PoolConexiones.getConexion();
    }

    public static void main(String[] args) {
        String sql = "SELECT * FROM videojuegos WHERE titulo = ?;";
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el titulo de un videojuego para mostrar sus datos: ");
            String videojuegoUsuario = sc.nextLine();
            ps.setString(1, videojuegoUsuario);
            ResultSet rs = ps.executeQuery();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
