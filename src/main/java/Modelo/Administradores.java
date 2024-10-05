package Modelo;

import Modelo.*;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

public class Administradores extends Usuarios {

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

    public void insertarProveedor(Proveedor prov) {
        try {
            Connection lce = con.getConectar1();
            String sql = "INSERT INTO proveedor (ruc,nombre,telefono,direccion,razon) values (?,?,?,?,?)";
            ps = lce.prepareStatement(sql);
            ps.setString(1, prov.getRuc());
            ps.setString(2, prov.getNombre());
            ps.setInt(3, prov.getTelefono());
            ps.setString(4, prov.getDireccion());
            ps.setString(5, prov.getRazonSocial());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar proveedor en admi" + ex);
            System.out.println("Error al insertar proveedor en admi" + ex);
        }
    }

    public boolean ModificarProveedor(Proveedor pr) {
        String sql = "UPDATE proveedor SET ruc = ?, nombre=?,telefono=?, direccion=?,razon=? WHERE id=?";
        try {

            ps = lce.prepareStatement(sql);
            ps.setString(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazonSocial());
            ps.setInt(6, pr.getId());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean ModificarUsuarios(Usuarios user) {
        String sql = "UPDATE clientes SET apellidos = ?, nombres=?,celular=?, dni=?,direccion=?,usuario=?,clave=?,tipo_usuario=?"
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
            ps.setInt(8, user.getTipo_usuario());
            ps.setInt(9, user.getId());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
            System.out.println(e);
            return false;
        }
    }

    public boolean ModificarProductos(Productos prod) {
        String sql = "UPDATE productos SET codigo_categoria = ?, nombre_producto=?,precio=?, stock=?,proveedor=?,fecha_vencimiento=?"
                + " WHERE idProducto=?";
        try {

            ps = lce.prepareStatement(sql);
            ps.setInt(1, prod.getCodigo_catategoria());
            ps.setString(2, prod.getNombre());
            ps.setFloat(3, prod.getPrecio());
            ps.setInt(4, prod.getStock());
            ps.setInt(5, prod.getProveedor());
            ps.setDate(6, prod.getFechaVancimiento());
            ps.setInt(7, prod.getIdProducto());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
            System.out.println(e);
            return false;
        }
    }

    public void ingresarUsuarios(Usuarios caracter) {
        try {
            Conexion l = new Conexion();
            Connection lce = l.getConectar1();
            String sql = "INSERT INTO clientes (apellidos,nombres, celular,dni,direccion,usuario,clave,tipo_usuario) values (?,?,?,?,?,?,?,?)";
            ps = lce.prepareStatement(sql);
            ps.setString(1, caracter.getApellidos());
            ps.setString(2, caracter.getNombres());
            ps.setString(3, caracter.getCelular());
            ps.setString(4, caracter.getDni());
            ps.setString(5, caracter.getDireccion());
            ps.setString(6, caracter.getUsuario());
            ps.setString(7, caracter.getClave());
            ps.setInt(8, caracter.getTipo_usuario());
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la consulta" + ex);
        }
    }

    public int existeUsuario(String usuario) {
        Conexion con = new Conexion();
        Connection lce = con.getConectar1();

        String sql = "SELECT count(idCliente) FROM clientes where usuario=?";
        try {
            ps = lce.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;
        } catch (SQLException es) {
            JOptionPane.showMessageDialog(null, "existe usuario" + es);
            return 1;
        }
    }

    public void mostarProductos(JTable tabla) throws ClassNotFoundException {
        Conexion l = new Conexion();
        Connection lce = l.getConectar1();
        Statement ps;
        ResultSet rs;
        String sql = "select tabla1.idProducto, tabla2.categoria,tabla1.nombre_producto,tabla1.precio,tabla1.stock,tabla3.nombre,tabla1.fecha_vencimiento\n" +
"from productos tabla1 inner join categoria tabla2 on (tabla1.codigo_categoria=tabla2.idCat)\n" +
"inner join proveedor tabla3 on (tabla1.proveedor=tabla3.id) order by tabla1.idProducto;     ";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Categoria");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("Proveedor");
        modelo.addColumn("F.vencimiento");
        tabla.setModel(modelo);
        String datos[] = new String[7];
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
                datos[6] = rs.getString(7);
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void mostarUsuarios(JTable tabla) throws ClassNotFoundException {
        Conexion l = new Conexion();
        Connection lce = l.getConectar1();
        Statement ps;
        ResultSet rs;
        String sql = "select t1.idCliente,t1.apellidos,t1.nombres,t1.celular,t1.dni,t1.direccion,t1.usuario,t1.clave,t2.nombre_usuario from\n" +
        "clientes t1 inner join tipoUsuario t2 on (t1.tipo_usuario=t2.tipo_usuario) order by idCliente";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Nombres");
        modelo.addColumn("celular");
        modelo.addColumn("Dni");
        modelo.addColumn("Direccion");
        modelo.addColumn("Correo");
        modelo.addColumn("Clave");
        modelo.addColumn("Acceso");

        tabla.setModel(modelo);
        Object datos[] = new Object[9];
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
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);

                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void mostarUsuariosParaVendedor(JTable tabla) throws ClassNotFoundException {
        Conexion l = new Conexion();
        Connection lce = l.getConectar1();
        Statement ps;
        ResultSet rs;
        String sql = "select t1.idCliente,t1.apellidos,t1.nombres,t1.celular,t1.dni,t1.direccion,t1.usuario,t1.clave,t2.nombre_usuario from \n"
                + "clientes t1 inner join tipoUsuario t2 on (t1.tipo_usuario=t2.tipo_usuario) where t2.nombre_usuario='cliente' order by idCliente";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Nombres");
        modelo.addColumn("celular");
        modelo.addColumn("Dni");
        modelo.addColumn("Direccion");
        modelo.addColumn("Correo");
        modelo.addColumn("Clave");
        modelo.addColumn("Acceso");

        tabla.setModel(modelo);
        Object datos[] = new Object[9];
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
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);

                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void mostrarProveedor(JTable tab) {
        Connection lce = con.getConectar1();
        String sql = "select id,ruc,nombre,telefono,direccion,razon from proveedor";
        DefaultTableModel mod = new DefaultTableModel();
        mod.addColumn("Id");
        mod.addColumn("Ruc");
        mod.addColumn("Nombre");
        mod.addColumn("Telefono");
        mod.addColumn("Direccion");
        mod.addColumn("Razon Social");
        tab.setModel(mod);
        Object datos[] = new Object[6];
        try {
            ps = lce.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                datos[0] = rs.getInt(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getInt(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                mod.addRow(datos);
            }
        } catch (SQLException ex) {
            System.out.println("Error en mostrar provvedor " + ex);
        }
    }

    public void mostrarVentas(JTable tab, String sql) throws ClassNotFoundException {
        Connection lce = con.getConectar1();
        DefaultTableModel mod = new DefaultTableModel();
        mod.addColumn("ID");
        mod.addColumn("Nombre Cliente");
        mod.addColumn("ID cliente");
        mod.addColumn("Vendedor");
        mod.addColumn("Fecha");
        mod.addColumn("tipo de pago");
        tab.setModel(mod);
        Object datos[] = new Object[6];
        try {
            ps = lce.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                datos[0] = rs.getInt(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getInt(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getDate(5);
                datos[5] = rs.getString(6);
                mod.addRow(datos);
            }
        } catch (SQLException ex) {
            System.out.println("Error en mostrar ventas" + ex);
        }
    }

    public void mostrarVentasPorFecha(JTable tab, Date fechaInicio, Date fechaFin) throws ClassNotFoundException {
        String sql = "SELECT ventaId, nombre_cliente, idCliente, vendedor, fecha, tipo_pago, subtotal FROM ventas "
                + "WHERE fecha BETWEEN ? AND ? ORDER BY fecha";

        DefaultTableModel mod = new DefaultTableModel();
        mod.addColumn("ID");
        mod.addColumn("Nombre Cliente");
        mod.addColumn("ID cliente");
        mod.addColumn("Vendedor");
        mod.addColumn("Fecha");
        mod.addColumn("Tipo de Pago");
        mod.addColumn("Subtotal");

        tab.setModel(mod);

        Object datos[] = new Object[7];

        try {
            ps = lce.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(2, new java.sql.Date(fechaFin.getTime()));
            rs = ps.executeQuery();

            while (rs.next()) {
                datos[0] = rs.getInt(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getInt(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getDate(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getDouble(7); // Asumí que el subtotal es de tipo Double, ajusta según sea necesario
                mod.addRow(datos);
            }

        } catch (SQLException ex) {
            System.out.println("Error en mostrar ventas: " + ex);

        }
    }
    public void mostrarProductosPorFecha(JTable tab, Date inicio,Date limite){
        try {
            String sql="select tab1.idProducto,tab2.categoria,tab1.nombre_producto,tab1.precio,tab1.stock,tab3.nombre,tab1.fecha_vencimiento \n" +
"from productos tab1 inner join categoria tab2 on (tab1.codigo_categoria=tab2.idCat) inner join proveedor tab3 on (tab1.proveedor=tab3.id)\n" +
"where tab1.fecha_vencimiento between ? and ? order by tab1.fecha_vencimiento asc;";
            DefaultTableModel mod=new DefaultTableModel();
            mod.addColumn("ID");
            mod.addColumn("Categoria");
            mod.addColumn("Nombre");
            mod.addColumn("Precio");
            mod.addColumn("Stock");
            mod.addColumn("Proveedor");
            mod.addColumn("F.vencimiento");
            tab.setModel(mod);
            ps=lce.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(inicio.getTime()));
            ps.setDate(2, new java.sql.Date(limite.getTime()));
            rs=ps.executeQuery();
            Object []datos=new Object[7];
            while(rs.next()){
                datos[0]=rs.getInt(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getDouble(4);
                datos[4]=rs.getInt(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getDate(7);
                mod.addRow(datos);
            }
        } catch (SQLException ex) {
            System.out.println("Error en pedir los datos en la clase admi"+ex);
        }
        
    }
    public void mostarVentasVendedor(JTable tab){
        try {
            String sql="select tab1.ventaId,tab1.nombre_cliente,tab1.vendedor,tab1.fecha,tab2.metodo,tab1.subtotal \n" +
"from ventasVendedor tab1 inner join tipopago tab2 on (tab1.tipo_pago=tab2.idTipoPago) order by tab1.fecha asc";
            DefaultTableModel mod=new DefaultTableModel();
            mod.addColumn("Id");
            mod.addColumn("Nombre Cliente");
            mod.addColumn("vendedor");
            mod.addColumn("fecha");
            mod.addColumn("tipo de pago");
            mod.addColumn("subtotal");
            tab.setModel(mod);
            Object []datos=new Object[6];
            ps=lce.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                datos[0]=rs.getInt(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getDate(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getDouble(6);
                mod.addRow(datos);
                
            }
        } catch (SQLException ex) {
            System.out.println("Error en mostar ventas del vendedor en la clase"+ex);
        }
    }
    public void mostrarVentasVendedorPorFechas(JTable tab,Date inicio,Date limite){
        try {
            String sql="select tab1.ventaId,tab1.nombre_cliente,tab1.vendedor,tab1.fecha,tab2.metodo,tab1.subtotal \n" +
            "from ventasVendedor tab1 inner join tipopago tab2 on (tab1.tipo_pago=tab2.idTipoPago) where tab1.fecha"
                    + " between ? and ? order by tab1.fecha asc;";
            DefaultTableModel mod=new DefaultTableModel();
            mod.addColumn("Id");
            mod.addColumn("Nombre Cliente");
            mod.addColumn("Vendedor");
            mod.addColumn("Fecha");
            mod.addColumn("Tipo de Pago");
            mod.addColumn("total");
            tab.setModel(mod);
            ps=lce.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(inicio.getTime()));
            ps.setDate(2,new java.sql.Date(limite.getTime()));
            rs=ps.executeQuery();
            Object[] datos=new Object[6];
            while(rs.next()){
                datos[0]=rs.getInt(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getDate(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getDouble(6);
                mod.addRow(datos);
            }
        } catch (SQLException ex) {
            System.out.println("Error en la seleccion de datos en la calse "+ex);
        }
        
    }
    public boolean EliminarDatos(int id1, String sql) {
        try {
            ps = lce.prepareStatement(sql);
            ps.setInt(1, id1);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public void eliminarfila(JTable tab) throws ClassNotFoundException {
        DefaultTableModel mode = (DefaultTableModel) tab.getModel();
        int seleccion = tab.getSelectedRow();
        if (seleccion == -1) {
            int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar esta venta", "pregunta",
                    JOptionPane.ERROR_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (JOptionPane.YES_OPTION == confirmar) {
                Connection lce = con.getConectar1();
                String sql = "DELETE ";
            }
        }
    }

    public void reporteVentaPDF(JTable tabla, String usuario,int numero) {
        Document doc = new Document();
        try {
            String ruta = System.getProperty("user.home");

            PdfWriter.getInstance(doc, new FileOutputStream(ruta + "/Downloads/" + "Reporte"+numero+".pdf"));
            doc.open();
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/main/java/imgPdf/logo4.jpeg");
            header.scaleToFit(500, 3000);
            header.setAlignment(Chunk.ALIGN_LEFT);
            Paragraph as = new Paragraph();
            as.setAlignment(Chunk.ALIGN_LEFT);
            as.add("Gracias por su preferencia. \n ");
            as.add("\n Nombre: " + usuario + "\n \n");

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
            System.out.println("e1" + ex);
        } catch (IOException ex) {
            System.out.println("e2" + ex);
        }

    }

    public void mostrardatos(String valor, JTextField valor1, JTextField valor2, JTextField valor3) {
        Conexion con = new Conexion();
        Connection lce = con.getConectar1();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "";
        sql = "SELECT nombre_producto,precio,stock FROM Productos WHERE (idProducto='" + valor + "' OR nombre_producto='" + valor + "')";

        Object[] datos = new Object[3];
        try {
            ps = lce.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                valor1.setText((String) datos[0]);
                valor2.setText((String) datos[1]);
                valor3.setText((String) datos[2]);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void reportes() {
        String sql = "select ventaId,nombre_cliente,idCliente,vendedor,fecha,tipo_pago,subtotal from ventas \n"
                + "where fecha='2023-11-30' and '2023-11-08' group by ventaId,nombre_cliente,idCliente,vendedor,fecha,tipo_pago,subtotal";

    }
}
