package Controlador.bancos;

// MISHEL LOEIZA 9959-23-3457
// Modelo para la tabla devolucion
// Nombres de los getters, setters y variables igual al nombre en base de datos

import java.sql.Timestamp;

public class devolucion {

    private int id_devolucion;
    private int id_prestamo;
    private Timestamp fecha_devolucion;
    private String observaciones;

    // Constructor vacío
    public devolucion() {
    }

    // Constructor con todos los campos
    public devolucion(int id_devolucion, int id_prestamo, Timestamp fecha_devolucion, String observaciones) {
        this.id_devolucion = id_devolucion;
        this.id_prestamo = id_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "devolucion{" +
                "id_devolucion=" + id_devolucion +
                ", id_prestamo=" + id_prestamo +
                ", fecha_devolucion=" + fecha_devolucion +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }

    // Getters y Setters
    public int getId_devolucion() {
        return id_devolucion;
    }

    public void setId_devolucion(int id_devolucion) {
        this.id_devolucion = id_devolucion;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public Timestamp getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Timestamp fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
