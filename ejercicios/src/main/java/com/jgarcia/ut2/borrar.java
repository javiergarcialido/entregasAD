package com.jgarcia.ut2;

import java.sql.*;

public class borrar {
    public static Connection getConnection() throws SQLException {
        return PoolConexiones.getConexion();
    }
    public static void main(String[] args)
    {
        String sql = "DELETE FROM videojuegos WHERE titulo = ?";
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        )
        {
            //borramos un registro con prepared statement
            ps.setString(1,"Legend of Zelda");
            ps.executeUpdate();
            System.out.println("Registro eliminado");
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
