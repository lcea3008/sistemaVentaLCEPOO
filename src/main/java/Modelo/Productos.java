package Modelo;

import java.sql.Date;
import javax.swing.JButton;

public class Productos {
    public int idProducto;
    public int codigo_catategoria;
   
    public String nombre;
    public float precio;
    public int Stock;
    public int proveedor;
    public Date fechaIngreso;
    public Date fechaVencimiento;

    public Productos(int idProducto, int codigo_catategoria, String nombre, float precio, int Stock, int proveedor, Date fechaVencimiento) {
        this.idProducto = idProducto;
        this.codigo_catategoria = codigo_catategoria;
        this.nombre = nombre;
        this.precio = precio;
        this.Stock = Stock;
        this.proveedor = proveedor;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Productos() {
    }

        
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public int getCodigo_catategoria() {
        return codigo_catategoria;
    }

    public void setCodigo_catategoria(int codigo_catategoria) {
        this.codigo_catategoria = codigo_catategoria;
    }

  
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngerso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaVancimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }


}
