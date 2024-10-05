/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LCE
 */
public class VentasVendedorDao {
    Conexion con=new Conexion();
    Connection lce=con.getConectar1();
    PreparedStatement ps;
    public void registarventas(VentasVendedor vend){
        String sql="INSERT INTO ventasVendedor (nombre_cliente,vendedor,tipo_pago,subtotal) VALUES (?,?,?,?)";
        try{
        ps=lce.prepareStatement(sql);
        ps.setString(1,vend.getNombre_cliente());
        ps.setString(2,vend.getVendedor());
        ps.setInt(3,vend.getTipoPago());
        ps.setDouble(4,vend.getSubtotal());
        ps.execute();
        } catch (SQLException ex) {
            System.out.println("Error en ventas vendedor dao "+ex);
        }
    }
}
