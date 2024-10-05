
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class RegistrarProductos extends Productos{
    
    public void insertarProductos(Productos prod) throws ClassNotFoundException {

        Conexion l = new Conexion();
        Connection lce = l.getConectar1();
        PreparedStatement ssp;
        String ab = "insert into productos(codigo_categoria,nombre_producto,precio,stock,proveedor,fecha_vencimiento) values (?,?,?,?,?,?)";
        
        try {
            ssp = lce.prepareStatement(ab);
            ssp.setInt(1, prod.getCodigo_catategoria());
            ssp.setString(2, prod.getNombre());
            ssp.setDouble(3, prod.getPrecio());
            ssp.setInt(4, prod.getStock());
            ssp.setInt(5,prod.getProveedor());
            ssp.setDate(6, prod.getFechaVancimiento());
            ssp.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
}
