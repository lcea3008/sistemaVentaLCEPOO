
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrosDAO extends RegistrarIngreso {
    Conexion con=new Conexion();
    Connection lce=con.getConectar1();
    PreparedStatement ps;
    
    
    public void insertaRegistros(RegistrarIngreso reg){
        String sql="INSERT into RegistroDeIngresos (idCliente,nombre,correo) values (?,?,?)";
        try {
            ps=lce.prepareStatement(sql);
            ps.setInt(1,reg.getIdCliente());
            ps.setString(2,reg.getNombre());
            ps.setString(3,reg.getCorreo());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Error en insertar registro "+ex);
        }
    }
}
