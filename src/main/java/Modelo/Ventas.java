package Modelo;

import Modelo.*;
import java.sql.Date;

public class Ventas {

    public String nombre_cliente;
    public int idCliente;
    public String fecha;
    public int vendedor;
    public int tipoPago;
    public float subtotal;

    public Ventas(String nombre_cliente, int idCliente, int tipoPago, float subtotal) {
        this.nombre_cliente = nombre_cliente;
        this.idCliente = idCliente;
        this.tipoPago = tipoPago;
        this.subtotal = subtotal;
    }
    

    public Ventas(String nombre_cliente, int tipoPago) {
        this.nombre_cliente = nombre_cliente;
        this.tipoPago = tipoPago;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
    
    
    public int getVendedor() {
        return vendedor;
    }

    public void setVendedor(int vendedor) {
        this.vendedor = vendedor;
    }

    public int getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }

    

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
