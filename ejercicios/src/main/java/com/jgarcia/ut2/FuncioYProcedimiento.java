package com.jgarcia.ut2;

import java.sql.*;

public class FuncioYProcedimiento {
    public static Connection getConnection() throws SQLException {
        return PoolConexiones.getConexion();
    }

    public static void main(String[] args) {
        String cuentaVideojuegos = "select cuentaVideojuegos()";
        String borraVideojuegos = "call borraVideojuegos(?)";

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jgarcia","root","root");
            PreparedStatement ps = con.prepareStatement(cuentaVideojuegos);
            CallableStatement cs = con.prepareCall(borraVideojuegos);
            ResultSet rs = ps.executeQuery())
        {
            rs.next();
            if(rs.wasNull())
                System.out.println("No hay videojuegos");
            else
            {
                int cont = rs.getInt(1);
                System.out.println("Número de videojuegos: " + cont);
            }

            //EJECUTANDO EL PROCEDIMIENTO
            cs.setString(1,"Minecraft");
            cs.execute();
            System.out.println("Videojuego eliminado de la Base de datos");

            /*
                CODIGO DE LA FUNCION

                create function cuentaVideojuegos() returns integer
                    language plpgsql
                as
                $$
                declare
                    contador integer;
                BEGIN
                    select count(*) into contador from videojuegos;
                    return contador;
                END;
                $$;


                CODIGO DEL PROCEDIMIENTO CON PARÁMETROS

                create procedure borraVideojuegos(titul VARCHAR)
                    language plpgsql
                as
                $$
                BEGIN
                    DELETE FROM videojuegos WHERE titulo = titul;
                END;
                $$;
             */
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
