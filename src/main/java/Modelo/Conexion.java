
package Modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {
    
    public Connection conectar=null;
    public Connection getConectar1(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost:3306/SistemaventaAbarrotero","root","password123456789");
            //JOptionPane.showMessageDialog(null, "conexion ex");
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error en el Driver");
            System.out.println("Error en el driver "+e);
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la conexion");
            System.out.println("Error en la conexion "+ex);
        }
        return conectar;
    }
}
