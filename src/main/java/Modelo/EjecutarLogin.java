package Modelo;

import Modelo.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EjecutarLogin extends Usuarios{

    public boolean comparacionDeUsuarios(Usuarios user) throws ClassNotFoundException {

        Conexion l = new Conexion();
        Connection lce = l.getConectar1();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT usuario,clave,tipo_usuario,nombres,apellidos,direccion,idCliente,dni,celular FROM clientes where usuario=?";

        try {
            ps = lce.prepareStatement(sql);
            ps.setString(1, user.getUsuario());
            rs = ps.executeQuery();
            if (rs.next()) {
                if (user.getClave().equals(rs.getString(2))) {
                    user.setNombres(rs.getString(4));
                    user.setTipo_usuario(rs.getInt(3));
                    user.setApellidos(rs.getString(5));
                    user.setDireccion(rs.getString(6));
                    user.setId(rs.getInt(7));
                    user.setDni(rs.getString(8));
                    user.setCelular(rs.getString(9));
                    return true; 
               }else{
                    return false;
                }
            }
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
    }

}
