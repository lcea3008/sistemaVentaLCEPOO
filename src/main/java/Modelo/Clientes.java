package Modelo;

import Modelo.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Clientes extends Usuarios {

    Conexion con = new Conexion();
    Connection lce = con.getConectar1();
    PreparedStatement ps;
    ResultSet rs;

    public void llenarCombo(JComboBox com, String consulta) {
        DefaultComboBoxModel mod = new DefaultComboBoxModel();
        mod.addElement("Seleccionar");
        Connection lce = con.getConectar1();

        try {
            ps = lce.prepareStatement(consulta);
            rs = ps.executeQuery();

            while (rs.next()) {
                mod.addElement(rs.getString(1));
                com.setModel(mod);
            }

            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex);
        }
    }

    public int IDVentas() {
        int idv = 0;
        String sql = "select max(ventaId) from ventas";
        try {
            Conexion con = new Conexion();
            Connection lce = con.getConectar1();
            PreparedStatement ps;
            ResultSet rs;
            ps = lce.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idv = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return idv;
    }

    public int IDCliente() {
        int idc = 0;
        String sql = "select max(idCliente) from clientes";
        try {
            Conexion con = new Conexion();
            Connection lce = con.getConectar1();
            PreparedStatement ps;
            ResultSet rs;
            ps = lce.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idc = rs.getInt(1);
            }
        } catch (SQLException ex) {

        }
        return idc;
    }

    public void mostaralProductos(JTable tabla, String cadena) throws ClassNotFoundException {
        Conexion l = new Conexion();
        Connection lce = l.getConectar1();
        Statement ps;
        ResultSet rs;
        String sql = cadena;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("F.Ingreso");
        modelo.addColumn("F.v");
        tabla.setModel(modelo);
        String datos[] = new String[6];

        try {
            ps = lce.createStatement();
            rs = ps.executeQuery(sql);
            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                //datos[6] = rs.getString(7);
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void eliminartodo(JTable tabla) {
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        int a = tabla.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);

        }
    }

    public boolean ModificarUsuarios(Usuarios user) {
        String sql = "UPDATE clientes SET apellidos = ?, nombres=?,celular=?, dni=?,direccion=?,usuario=?,clave=?"
                + " WHERE idcliente=?";
        try {

            ps = lce.prepareStatement(sql);
            ps.setString(1, user.getApellidos());
            ps.setString(2, user.getNombres());
            ps.setString(3, user.getCelular());
            ps.setString(4, user.getDni());
            ps.setString(5, user.getDireccion());
            ps.setString(6, user.getUsuario());
            ps.setString(7, user.getClave());
            ps.setInt(8, user.getId());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
            System.out.println(e);
            return false;
        }
    }

    public void Actualizar(String apellidos, String nombres, String celular, String dni, String direccion,
            String usuario, String clave) {
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea modificar los datos actuales?");

        if (confirmar == JOptionPane.YES_OPTION) {

            try {

                String Ssql = "UPDATE clientes SET apellidos=?, nombres=?, celular=?, dni=?, direccion=?, usuario=?,clave=? "
                        + "WHERE idCliente=?";

                PreparedStatement prest = lce.prepareStatement(Ssql);

                prest.setString(1, apellidos);
                prest.setString(2, nombres);
                prest.setString(3, celular);
                prest.setString(4, dni);
                prest.setString(5, direccion);
                prest.setString(6, usuario);
                prest.setString(7, clave);

                if (prest.executeUpdate() > 0) {

                    JOptionPane.showMessageDialog(null, "Los datos han sido modificados con éxito", "Operación Exitosa",
                            JOptionPane.INFORMATION_MESSAGE);

                } else {

                    JOptionPane.showMessageDialog(null, "No se ha podido realizar la actualización de los datos\n"
                            + "Inténtelo nuevamente.", "Error en la operación",
                            JOptionPane.ERROR_MESSAGE);

                }

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "No se ha podido realizar la actualización de los datos\n"
                        + "Inténtelo nuevamente.\n"
                        + "Error: " + e, "Error en la operación",
                        JOptionPane.ERROR_MESSAGE);

            }

        }

    }

    public void insertarVenta(Ventas venta) throws ClassNotFoundException {
        String insertVentaQuery = "INSERT INTO ventas (nombre_cliente, idCliente,tipo_pago,subtotal) VALUES (?, ?, ?,? )";
        Conexion con = new Conexion();
        Connection lce = con.getConectar1();
        try {

            PreparedStatement ps = lce.prepareStatement(insertVentaQuery);
            ps.setString(1, venta.getNombre_cliente());
            ps.setInt(2, venta.getIdCliente());
            ps.setInt(3, venta.getTipoPago());
            ps.setFloat(4, venta.getSubtotal());
            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el codigo insertar" + e);
        }
    }

    public void insertarVentaAdmi(VentasVendedor venta) throws ClassNotFoundException {
        String insertVentaQuery = "INSERT INTO ventasVendedor (nombre_cliente,vendedor,tipo_pago) VALUES ( ?, ?)";
        Conexion con = new Conexion();
        Connection connection = con.getConectar1();
        try {

            PreparedStatement ps = connection.prepareStatement(insertVentaQuery);
            ps.setString(1, venta.getNombre_cliente());
            ps.setInt(2, venta.getTipoPago());
            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void insertarDetalleventa(DetalleVenta vent) {
        try {
            Conexion con = new Conexion();
            Connection lce = con.getConectar1();
            PreparedStatement ps;
            ps = lce.prepareStatement("insert into detalleVenta (ventaId,idProducto,nombre_producto,precio_u,cantidad,subtotal) values (?,?,?,?,?,?)");
            ps.setInt(1, vent.getVentaid());
            ps.setInt(2, vent.getIdProducto());
            ps.setString(3, vent.getNombre_producto());
            ps.setFloat(4, vent.getPrecio_u());
            ps.setInt(5, vent.getCantidad());
            ps.setFloat(6, vent.getSubtotal());
            ps.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error en insertar datos" + ex);
        }
    }

    public void generarPDF(JTable tabla, String lab, String nombre, String direccion, String dni) {
        Document doc = new Document();
        try {
            String ruta = System.getProperty("user.home");

            PdfWriter.getInstance(doc, new FileOutputStream(ruta + "/Desktop/" + lab + ".pdf"));
            doc.open();
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/main/java/imgPdf/logo4.jpeg");
            header.scaleToFit(500, 3000);
            header.setAlignment(Chunk.ALIGN_LEFT);
            Paragraph as = new Paragraph();
            as.setAlignment(Chunk.ALIGN_LEFT);
            as.add("Gracias por su preferencia. \n ");
            as.add("\n Nombre: " + nombre);
            as.add("\n Direccion: " + direccion + "                                               \tDni:" + dni + "\n \n \n");

            doc.add(header);
            doc.add(as);
            PdfPTable pdfTable = new PdfPTable(tabla.getColumnCount());
            for (int i = 0; i < tabla.getColumnCount(); i++) {
                pdfTable.addCell(tabla.getColumnName(i));
            }
            for (int row = 0; row < tabla.getRowCount(); row++) {
                for (int col = 0; col < tabla.getColumnCount(); col++) {
                    pdfTable.addCell(tabla.getValueAt(row, col).toString());
                }
            }
            doc.add(pdfTable);
            Paragraph p2 = new Paragraph();
            p2.setAlignment(Chunk.ALIGN_RIGHT);
            p2.add("\n \n Gracias por su preferencia. \n");
            doc.add(p2);
            doc.close();
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Err" + ex);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error en el pdf clientes " + ex);
            System.out.println("e1" + ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de excepcion pdf clientes " + ex);
            System.out.println("e2" + ex);
        }
    }

    public void generarBoleta(JTable tabla1, JTable tabla2) {
        DefaultTableModel mod = new DefaultTableModel();

        mod.addColumn("Nombre");
        mod.addColumn("Precio U.");
        mod.addColumn("Cantidad");
        mod.addColumn("total");
        tabla2.setModel(mod);
        for (int i = 0; i < tabla1.getRowCount(); i++) {
            String datos[] = new String[4];
            datos[0] = tabla1.getValueAt(i, 1).toString();
            datos[1] = tabla1.getValueAt(i, 2).toString();
            datos[2] = tabla1.getValueAt(i, 3).toString();
            datos[3] = tabla1.getValueAt(i, 4).toString();

            mod.addRow(datos);

        }

    }

    public void mostrarFechaActual(JLabel lab1, JLabel lab2) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        Date fechaActual = new Date();
        String horaFormateada = formatoHora.format(fechaActual);
        String fechaFormateada = formatoFecha.format(fechaActual);

        lab1.setText(horaFormateada);
        lab2.setText(fechaFormateada);
    }

    public void detallesVenta(DetalleVenta det) throws ClassNotFoundException {
        String insert = "INSERT INTO detalleVenta (ventaId,idProducto,nombre_producto, precio_u,cantidad,totalProducto) VALUES (?, ?, ?,?,?,?)";
        Conexion con = new Conexion();
        Connection connection = con.getConectar1();
        try {

            PreparedStatement ps = connection.prepareStatement(insert);
            ps.setInt(1, det.getVentaid());
            ps.setInt(2, det.getIdProducto());
            ps.setString(3, det.getNombre_producto());
            ps.setFloat(4, det.getPrecio_u());
            ps.setInt(5, det.getCantidad());
            ps.setFloat(6, det.getSubtotal());
            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }




}
