/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.bodegas;

import Controlador.bodegas.Bodega; 
import Modelo.Conexion;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class BodegaDAO {
    
    private static final String SQL_SELECT = "SELECT pkid, fkidtipobodega, nombre, direccion, estado FROM bodega";
    private static final String SQL_INSERT = "INSERT INTO bodega (pkid, fkidtipobodega, nombre, direccion, estado) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE bodega SET fkidtipobodega=?, nombre=?, direccion=?, estado=? WHERE pkid=? ";
    private static final String SQL_DELETE = "DELETE FROM bodega WHERE pkid=?";
    private static final String SQL_QUERY = "SELECT pkid, fkidtipobodega, nombre, direccion, estado FROM bodega WHERE pkid=?";
    
    public List<Bodega> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bodega> list_bodegaes = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Bodega bodega = new Bodega();
                bodega.setPkid(rs.getString("pkid"));
                bodega.setFkidtipobodega(rs.getString("fkidtipobodega"));
                bodega.setNombre(rs.getString("nombre"));
                bodega.setDireccion(rs.getString("direccion"));
                bodega.setEstado(rs.getString("estado"));
                
                list_bodegaes.add(bodega);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return list_bodegaes;
    }
    public int insert(Bodega bodega) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, bodega.getPkid());
            stmt.setString(2, bodega.getFkidtipobodega());
            stmt.setString(3, bodega.getNombre());
            stmt.setString(4, bodega.getDireccion());
            stmt.setString(5, bodega.getEstado());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    public int update(Bodega bodega) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            
            
            stmt.setString(1, bodega.getFkidtipobodega());
            stmt.setString(2, bodega.getNombre());
            stmt.setString(3, bodega.getDireccion());
            stmt.setString(4, bodega.getEstado());       
            stmt.setString(5, bodega.getPkid());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    public int delete(Bodega bodega) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, bodega.getPkid());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public Bodega query(Bodega bodega) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, bodega.getPkid());
            rs = stmt.executeQuery();
            if (rs.next()) {
                bodega.setFkidtipobodega(rs.getString("fkidtipobodega"));
                bodega.setNombre(rs.getString("nombre"));
                bodega.setDireccion(rs.getString("direccion"));
                bodega.setEstado(rs.getString("estado"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return bodega;
    }
    
    public void imprimirReporte() {
        Connection conn = null;
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;
        try {
            conn = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/ReportePrueba/"+ "ReportePrueba.jrxml");
            print = JasperFillManager.fillReport(report, p, conn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Bodegaes");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
