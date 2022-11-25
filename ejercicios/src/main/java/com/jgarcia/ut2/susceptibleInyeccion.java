package com.jgarcia.ut2;

import java.sql.*;
import java.util.Scanner;

public class susceptibleInyeccion {
    public static Connection getConnection() throws SQLException {
        return PoolConexiones.getConexion();
    }

    public static void main(String[] args) {
        try(Connection con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null)
        {
            Scanner sc = new Scanner(System.in);

            System.out.println("Introduce un libro para buscarlo en la BD: ");
            //le pedimos al usuario que introduzca un videojuego
            String videojuego = sc.nextLine();

            //consultamos los datos introducidos
            String sql = "SELECT * FROM videojuegos WHERE titulo = '" + videojuego + "';";

            /*Que pasaria si el usuario introduce:

                '; DROP TABLE videojuegos --

            - La comilla simple del principio coincidiría con la que ya está en la consulta.
            - Los dos guiones del final significan que lo que se encuentre después de ellos se interpretará como
            comentarios.
            - Por tanto la consulta resultante sería la siguiente

                SELECT * FROM videojuegos WHERE titulo = ' ';
                DROP TABLE videojuegos;

            Esto haría que se eliminara la tabla de videojuegos entera
             */

            System.exit(0);

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
