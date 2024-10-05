/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LCE
 */
public class RegistroDeReportesDAO {
     
    PreparedStatement ps;
    ResultSet rs;
    Conexion as = new Conexion();
    Connection lce = as.getConectar1();
    public void descargarReporte(RegistroDeReportes desc) {
        try {
            String sql = "insert into registro_reportes (idCliente) values (?)";
            ps = lce.prepareStatement(sql);
            ps.setInt(1,desc.getIdCliente());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("error en la clase registro de reportes");
        }
    }

    public int idreporte() {
        int idreport = 0;
        try {
            
            String sql = "select max(idReporte) from registro_reportes";
            ps=lce.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                idreport=rs.getInt(1);
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener el id del reporte");
        }
        return idreport;
    }
}
