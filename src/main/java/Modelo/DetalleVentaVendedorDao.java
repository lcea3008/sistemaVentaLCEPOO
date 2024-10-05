package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DetalleVentaVendedorDao {
    public int IDVentasVendedor() {
        int idvVendedor = 0;
        String sql = "select max(ventaId) from ventasVendedor";
        try {
            Conexion con = new Conexion();
            Connection lce = con.getConectar1();
            PreparedStatement ps;
            ResultSet rs;
            ps = lce.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idvVendedor = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return idvVendedor;
    }

    public void insertaDetalleVentaVendedor(DetalleVentaVendedor vent) {
        try {
            Conexion con = new Conexion();
            Connection lce = con.getConectar1();
            PreparedStatement ps;
            String sql = "INSERT INTO detalleVentaVendedor (idVenta,idProducto,nombre_producto,precio_u,cantidad,totalProducto)"
                    + "values (?,?,?,?,?,?)  ";
            ps = lce.prepareStatement(sql);
            ps.setInt(1, vent.getIdventa());
            ps.setInt(2, vent.getIdProducto());
            ps.setString(3, vent.getNombre_producto());
            ps.setDouble(4, vent.getPrecio_u());
            ps.setInt(5, vent.getCantidad());
            ps.setDouble(6, vent.getTotal_producto());
            ps.execute();

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(parentComponent, ex, nombre_producto, idDetalle);
            System.out.println("Error en insertar detalle ventas vendedor" +ex);
        }
    }
}
