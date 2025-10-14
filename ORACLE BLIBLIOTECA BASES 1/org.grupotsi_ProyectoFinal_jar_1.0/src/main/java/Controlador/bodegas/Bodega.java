/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.bodegas;

/**
 *
 * @author visitante
 */
public class Bodega {
    
    String pkid;
    String fkidtipobodega;
    String nombre;
    String direccion;
    String estado;

    public Bodega(String pkid, String fkidtipobodega, String nombre, String direccion, String estado) {
        this.pkid = pkid;
        this.fkidtipobodega = fkidtipobodega;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Bodega{" + "pkid=" + pkid + ", fkidtipobodega=" + fkidtipobodega + ", nombre=" + nombre + ", direccion=" + direccion + ", estado=" + estado + '}';
    }

    public String getPkid() {
        return pkid;
    }

    public void setPkid(String pkid) {
        this.pkid = pkid;
    }

    public String getFkidtipobodega() {
        return fkidtipobodega;
    }

    public void setFkidtipobodega(String fkidtipobodega) {
        this.fkidtipobodega = fkidtipobodega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Bodega() {
    }
   
}
