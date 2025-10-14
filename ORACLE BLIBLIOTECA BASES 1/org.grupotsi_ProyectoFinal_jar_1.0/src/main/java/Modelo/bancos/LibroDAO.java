package Modelo.bancos;
//MISHEL LOEIZA 9959-23-3457


import Controlador.bancos.libro;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    private static final String SQL_SELECT = "SELECT id_libro, titulo, autor, editorial, categoria, stock, estado FROM libro";
    private static final String SQL_INSERT = "INSERT INTO libro(titulo, autor, editorial, categoria, stock, estado) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE libro SET titulo = ?, autor = ?, editorial = ?, categoria = ?, stock = ?, estado = ? WHERE id_libro = ?";
    private static final String SQL_DELETE = "DELETE FROM libro WHERE id_libro = ?";
    private static final String SQL_QUERY = "SELECT id_libro, titulo, autor, editorial, categoria, stock, estado FROM libro WHERE id_libro = ?";

    public List<libro> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<libro> list_libros = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                libro lib = new libro();
                lib.setId_libro(rs.getInt("id_libro"));
                lib.setTitulo(rs.getString("titulo"));
                lib.setAutor(rs.getString("autor"));
                lib.setEditorial(rs.getString("editorial"));
                lib.setCategoria(rs.getString("categoria"));
                lib.setStock(rs.getInt("stock"));
                lib.setEstado(rs.getString("estado"));
                list_libros.add(lib);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_libros;
    }

    public int insert(libro lib) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, lib.getTitulo());
            stmt.setString(2, lib.getAutor());
            stmt.setString(3, lib.getEditorial());
            stmt.setString(4, lib.getCategoria());
            stmt.setInt(5, lib.getStock());
            stmt.setString(6, lib.getEstado());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(libro lib) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, lib.getTitulo());
            stmt.setString(2, lib.getAutor());
            stmt.setString(3, lib.getEditorial());
            stmt.setString(4, lib.getCategoria());
            stmt.setInt(5, lib.getStock());
            stmt.setString(6, lib.getEstado());
            stmt.setInt(7, lib.getId_libro());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(libro lib) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, lib.getId_libro());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public libro query(libro lib) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, lib.getId_libro());
            rs = stmt.executeQuery();
            if (rs.next()) {
                lib.setTitulo(rs.getString("titulo"));
                lib.setAutor(rs.getString("autor"));
                lib.setEditorial(rs.getString("editorial"));
                lib.setCategoria(rs.getString("categoria"));
                lib.setStock(rs.getInt("stock"));
                lib.setEstado(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return lib;
    }

    public boolean existeLibro(int idLibro) {
        String sql = "SELECT 1 FROM libro WHERE id_libro = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idLibro);
            rs = stmt.executeQuery();
            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return existe;
    }
}
