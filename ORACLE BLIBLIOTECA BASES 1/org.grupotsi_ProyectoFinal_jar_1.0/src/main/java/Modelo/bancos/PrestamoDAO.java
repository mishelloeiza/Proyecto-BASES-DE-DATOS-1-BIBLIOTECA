package Modelo.bancos;
// MISHEL LOEIZA 9959-23-3457

import Controlador.bancos.prestamo;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    private static final String SQL_SELECT = "SELECT id_prestamo, id_libro, id_usuario, fecha_prestamo, estado FROM prestamo";
    private static final String SQL_INSERT = "INSERT INTO prestamo(id_libro, id_usuario, estado) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE prestamo SET id_libro = ?, id_usuario = ?, fecha_prestamo = ?, estado = ? WHERE id_prestamo = ?";
    private static final String SQL_DELETE = "DELETE FROM prestamo WHERE id_prestamo = ?";
    private static final String SQL_QUERY = "SELECT id_prestamo, id_libro, id_usuario, fecha_prestamo, estado FROM prestamo WHERE id_prestamo = ?";

    public List<prestamo> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<prestamo> listaPrestamos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                prestamo p = new prestamo();
                p.setId_prestamo(rs.getInt("id_prestamo"));
                p.setId_libro(rs.getInt("id_libro"));
                p.setId_usuario(rs.getInt("id_usuario"));
                p.setFecha_prestamo(rs.getTimestamp("fecha_prestamo")); // ✅ Timestamp
                p.setEstado(rs.getString("estado"));
                listaPrestamos.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return listaPrestamos;
    }

    public int insert(prestamo p) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, p.getId_libro());
            stmt.setInt(2, p.getId_usuario());
            stmt.setString(3, p.getEstado());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(prestamo p) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, p.getId_libro());
            stmt.setInt(2, p.getId_usuario());
            stmt.setTimestamp(3, p.getFecha_prestamo()); // ✅ Timestamp
            stmt.setString(4, p.getEstado());
            stmt.setInt(5, p.getId_prestamo());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(prestamo p) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, p.getId_prestamo());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public prestamo query(prestamo p) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, p.getId_prestamo());
            rs = stmt.executeQuery();
            if (rs.next()) {
                p.setId_libro(rs.getInt("id_libro"));
                p.setId_usuario(rs.getInt("id_usuario"));
                p.setFecha_prestamo(rs.getTimestamp("fecha_prestamo")); // ✅ Timestamp
                p.setEstado(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return p;
    }

    public boolean existePrestamo(int idPrestamo) {
        String sql = "SELECT 1 FROM prestamo WHERE id_prestamo = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPrestamo);
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
