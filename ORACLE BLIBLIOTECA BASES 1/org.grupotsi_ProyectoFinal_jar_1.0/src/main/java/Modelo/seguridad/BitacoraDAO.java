package Modelo.seguridad;

import Controlador.seguridad.Bitacora;
import Modelo.Conexion;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BitacoraDAO {

    private static final String SQL_SELECT = "SELECT id_bitacora, id_usuario, id_aplicacion, fecha, ip, accion, nombre_pc FROM bitacora";
    private static final String SQL_INSERT = "INSERT INTO bitacora(id_usuario, id_aplicacion, fecha, ip, accion, nombre_pc) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_QUERY = "SELECT id_bitacora, id_usuario, id_aplicacion, fecha, ip, accion, nombre_pc FROM bitacora WHERE fecha BETWEEN TO_TIMESTAMP(?, 'YYYY-MM-DD') AND TO_TIMESTAMP(?, 'YYYY-MM-DD')";

    // Método para registrar una acción en la bitácora
    public int registrarAccionEnBitacora(int idUsuario, int idAplicacion, String accion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idAplicacion);
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // Fecha actual
            stmt.setString(4, obtenerIP());
            stmt.setString(5, accion);
            stmt.setString(6, obtenerNombrePC());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public String obtenerIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "0.0.0.0";
        }
    }

    public String obtenerNombrePC() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Desconocido";
        }
    }

    public List<Bitacora> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bitacora> bitacoras = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Bitacora bitacora = new Bitacora();
                bitacora.setIdBitacora(rs.getInt("id_bitacora"));
                bitacora.setIdUsuario(rs.getInt("id_usuario"));
                bitacora.setIdAplicacion(rs.getInt("id_aplicacion"));
                bitacora.setFecha(rs.getString("fecha"));
                bitacora.setIp(rs.getString("ip"));
                bitacora.setAccion(rs.getString("accion"));
                bitacora.setNombrePc(rs.getString("nombre_pc"));
                bitacoras.add(bitacora);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return bitacoras;
    }

    public int insert(Bitacora bitacora) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, bitacora.getIdUsuario());
            stmt.setInt(2, bitacora.getIdAplicacion());
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.setString(4, bitacora.getIp());
            stmt.setString(5, bitacora.getAccion());
            stmt.setString(6, bitacora.getNombrePc());

            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public List<Bitacora> query(String primeraFecha, String segundaFecha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bitacora> bitacoras = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, primeraFecha);
            stmt.setString(2, segundaFecha);
            rs = stmt.executeQuery();
            System.out.println("Query ejecutada: " + stmt);
            while (rs.next()) {
                Bitacora bitacora = new Bitacora();
                bitacora.setIdBitacora(rs.getInt("id_bitacora"));
                bitacora.setIdUsuario(rs.getInt("id_usuario"));
                bitacora.setIdAplicacion(rs.getInt("id_aplicacion"));
                bitacora.setFecha(rs.getString("fecha"));
                bitacora.setIp(rs.getString("ip"));
                bitacora.setAccion(rs.getString("accion"));
                bitacora.setNombrePc(rs.getString("nombre_pc"));
                bitacoras.add(bitacora);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return bitacoras;
    }
}
