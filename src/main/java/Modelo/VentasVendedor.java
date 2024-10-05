package Modelo;

import Modelo.*;
import java.sql.Date;

public class VentasVendedor {
    public int id;
    public String nombre_cliente;
    public String fecha;
    public String vendedor;
    public int tipoPago;
    public double subtotal;

    public VentasVendedor(String nombre_cliente, String vendedor, int tipoPago, double subtotal) {
        this.nombre_cliente = nombre_cliente;
        this.vendedor = vendedor;
        this.tipoPago = tipoPago;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public int getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
