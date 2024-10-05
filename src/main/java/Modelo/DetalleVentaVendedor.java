package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DetalleVentaVendedor {

    public int idDetalle;
    public int idventa;
    public int idProducto;
    public String nombre_producto;
    public double precio_u;
    public int cantidad;
    public double total_producto;

    public DetalleVentaVendedor(int idventa, int idProducto, String nombre_producto, double precio_u, int cantidad, double total_producto) {
        this.idventa = idventa;
        this.idProducto = idProducto;
        this.nombre_producto = nombre_producto;
        this.precio_u = precio_u;
        this.cantidad = cantidad;
        this.total_producto = total_producto;
    }

    public DetalleVentaVendedor() {
    }
    
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public double getPrecio_u() {
        return precio_u;
    }

    public void setPrecio_u(double precio_u) {
        this.precio_u = precio_u;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal_producto() {
        return total_producto;
    }

    public void setTotal_producto(double total_producto) {
        this.total_producto = total_producto;
    }

    
}
