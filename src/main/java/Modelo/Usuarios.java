package Modelo;

public class Usuarios{
    private int id;
    private String apellidos;
    private String nombres;
    private String celular;
    private String dni;
    private String direccion;
    private String usuario;
    private String clave;
    private int tipo_usuario;

    public Usuarios(int id, String apellidos, String nombres, String celular, String dni, String direccion, String usuario, String clave, int tipo_usuario) {
        this.id = id;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.celular = celular;
        this.dni = dni;
        this.direccion = direccion;
        this.usuario = usuario;
        this.clave = clave;
        this.tipo_usuario = tipo_usuario;
    }

    public Usuarios(int id, String apellidos, String nombres, String celular, String dni, String direccion, String usuario, String clave) {
        this.id = id;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.celular = celular;
        this.dni = dni;
        this.direccion = direccion;
        this.usuario = usuario;
        this.clave = clave;
    }

    

    

    public Usuarios() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
}
