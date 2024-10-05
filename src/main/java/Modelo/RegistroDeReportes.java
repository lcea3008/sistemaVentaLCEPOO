package Modelo;


import java.sql.Date;


public class RegistroDeReportes {

   public int idreporte;
    public int idCliente;
    public Date fecha;

    public RegistroDeReportes(int idCliente) {
        this.idCliente = idCliente;
    }

    public RegistroDeReportes() {
    }
    
    public int getIdreporte() {
        return idreporte;
    }

    public void setIdreporte(int idreporte) {
        this.idreporte = idreporte;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
}
