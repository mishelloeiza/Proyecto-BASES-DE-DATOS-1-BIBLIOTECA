package Modelo.biblioteca;
// MISHEL LOEIZA 9959-23-3457

import Controlador.biblioteca.devolucion;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DevolucionDAO {

    private static final String SQL_INSERT = "INSERT INTO devolucion(id_prestamo, observaciones) VALUES (?, ?)";
    private static final String SQL_SELECT = "SELECT id_devolucion, id_prestamo, fecha_devolucion, observaciones FROM devolucion";
    private static final String SQL_QUERY = "SELECT id_devolucion, id_prestamo, fecha_devolucion, observaciones FROM devolucion WHERE id_devolucion = ?";
    private static final String SQL_DELETE = "DELETE FROM devolucion WHERE id_devolucion = ?";
    private static final String SQL_UPDATE = "UPDATE devolucion SET id_prestamo = ?, observaciones = ? WHERE id_devolucion = ?";

    // ✅ Insertar nueva devolución
    public int insert(devolucion d) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, d.getId_prestamo());
            stmt.setString(2, d.getObservaciones());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    // ✅ Listar todas las devoluciones
    public List<devolucion> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<devolucion> listaDevoluciones = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                devolucion d = new devolucion();
                d.setId_devolucion(rs.getInt("id_devolucion"));
                d.setId_prestamo(rs.getInt("id_prestamo"));
                d.setFecha_devolucion(rs.getTimestamp("fecha_devolucion"));
                d.setObservaciones(rs.getString("observaciones"));
                listaDevoluciones.add(d);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return listaDevoluciones;
    }

    // ✅ Consultar una devolución por ID
    public devolucion query(devolucion d) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, d.getId_devolucion());
            rs = stmt.executeQuery();
            if (rs.next()) {
                d.setId_prestamo(rs.getInt("id_prestamo"));
                d.setFecha_devolucion(rs.getTimestamp("fecha_devolucion"));
                d.setObservaciones(rs.getString("observaciones"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return d;
    }

    // ✅ Eliminar una devolución por ID
    public int delete(devolucion d) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, d.getId_devolucion());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    // ✅ Actualizar devolución existente
    public int update(devolucion d) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, d.getId_prestamo());
            stmt.setString(2, d.getObservaciones());
            stmt.setInt(3, d.getId_devolucion());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
}
