package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    // URL para Oracle XE 21c con PDB XEPDB1
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String JDBC_USER = "biblioteca";
    private static final String JDBC_PASS = "biblioteca123";

    private static Connection conexion;

    public static Connection getConnection() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("oracle.jdbc.OracleDriver");
                conexion = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
                System.out.println("✅ Conexión exitosa a Oracle XE como " + JDBC_USER);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ No se encontró el driver JDBC de Oracle.");
            e.printStackTrace(System.out);
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con Oracle XE.");
            e.printStackTrace(System.out);
        }
        return conexion;
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
