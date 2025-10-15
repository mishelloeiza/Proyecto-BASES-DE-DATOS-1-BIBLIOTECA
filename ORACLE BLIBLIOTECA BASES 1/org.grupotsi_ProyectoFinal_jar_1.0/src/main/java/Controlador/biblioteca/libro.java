package Controlador.biblioteca;


// MISHEL LOEIZA 9959-23-3457
// Nombres de los getters, setters y variables igual al nombre en base de datos
// Nombre del archivo Java igual que la tabla
// Se usó encapsulate field para get y set

public class libro {

    private int id_libro;
    private String titulo;
    private String autor;
    private String editorial;
    private String categoria;
    private int stock;
    private String estado;

    // Constructor vacío
    public libro() {
    }

    // Constructor con todos los campos
    public libro(int id_libro, String titulo, String autor, String editorial, String categoria, int stock, String estado) {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
        this.stock = stock;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "libro{" +
                "id_libro=" + id_libro +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                ", categoria='" + categoria + '\'' +
                ", stock=" + stock +
                ", estado='" + estado + '\'' +
                '}';
    }

    // Getters y Setters
    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
