package Controlador.bancos;

// MISHEL LOEIZA 9959-23-3457
// Nombres de los getters, setters y variables igual al nombre en base de datos
// Nombre del archivo Java igual que la tabla
// Se usó encapsulate field para get y set

import java.sql.Timestamp;

public class prestamo {

    private int id_prestamo;
    private int id_libro;
    private int id_usuario;
    private Timestamp fecha_prestamo; // ✅ ahora es Timestamp
    private String estado;

    // Constructor vacío
    public prestamo() {
    }

    // Constructor con todos los campos
    public prestamo(int id_prestamo, int id_libro, int id_usuario, Timestamp fecha_prestamo, String estado) {
        this.id_prestamo = id_prestamo;
        this.id_libro = id_libro;
        this.id_usuario = id_usuario;
        this.fecha_prestamo = fecha_prestamo;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "prestamo{" +
                "id_prestamo=" + id_prestamo +
                ", id_libro=" + id_libro +
                ", id_usuario=" + id_usuario +
                ", fecha_prestamo=" + fecha_prestamo +
                ", estado='" + estado + '\'' +
                '}';
    }

    // Getters y Setters
    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Timestamp getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(Timestamp fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
