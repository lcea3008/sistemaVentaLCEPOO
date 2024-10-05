
package Modelo;

public class DetalleVenta extends Productos{
    public int ventaid;
    public int idProducto;
    public String nombre_producto;
    public float precio_u;
    public int cantidad;
    public float totalProducto;

    public int getVentaid() {
        return ventaid;
    }

    public void setVentaid(int ventaid) {
        this.ventaid = ventaid;
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

    public float getPrecio_u() {
        return precio_u;
    }

    public void setPrecio_u(float precio_u) {
        this.precio_u = precio_u;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotal() {
        return totalProducto;
    }

    public void setSubtotal(float totalProducto) {
        this.totalProducto = totalProducto;
    }
    
}
