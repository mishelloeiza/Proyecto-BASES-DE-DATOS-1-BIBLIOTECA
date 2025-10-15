package vista.bancos;

import Controlador.seguridad.UsuarioConectado;
import Modelo.seguridad.UsuarioDAO;
import Controlador.seguridad.permisos;

import Modelo.bancos.PrestamoDAO;
import Modelo.bancos.LibroDAO;
import Controlador.bancos.prestamo;
import Controlador.bancos.libro;
import Controlador.seguridad.Bitacora;
import Modelo.Conexion;
import java.io.File;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class MantenimientoPrestamo extends javax.swing.JInternalFrame {

    int APLICACION = 103;
    private Connection connectio;
    private int idUsuarioSesion;
    private UsuarioDAO usuarioDAO;
    private permisos permisos;
    private permisos permisosUsuarioActual;

    public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Préstamo");
        modelo.addColumn("ID Libro");
        modelo.addColumn("ID Usuario");
        modelo.addColumn("Fecha Préstamo");
        modelo.addColumn("Estado");

        PrestamoDAO prestamoDAO = new PrestamoDAO();
        List<prestamo> listaPrestamos = prestamoDAO.select();
        TABLAP.setModel(modelo);
        String[] dato = new String[5];

        for (int i = 0; i < listaPrestamos.size(); i++) {
            dato[0] = Integer.toString(listaPrestamos.get(i).getId_prestamo());
            dato[1] = Integer.toString(listaPrestamos.get(i).getId_libro());
            dato[2] = Integer.toString(listaPrestamos.get(i).getId_usuario());

            Timestamp fecha = listaPrestamos.get(i).getFecha_prestamo();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dato[3] = formato.format(fecha);

            dato[4] = listaPrestamos.get(i).getEstado();
            modelo.addRow(dato);
        }
    }

    public void registrarPrestamo() {
        int idLibro = Integer.parseInt(txtIdLibro.getText());
        int idUsuario = UsuarioConectado.getIdUsuario();

        LibroDAO libroDAO = new LibroDAO();
        libro libroConsultado = new libro();
        libroConsultado.setId_libro(idLibro);
        libroConsultado = libroDAO.query(libroConsultado);

        if (libroConsultado.getStock() > 0) {
            prestamo nuevoPrestamo = new prestamo();
            nuevoPrestamo.setId_libro(idLibro);
            nuevoPrestamo.setId_usuario(idUsuario);
            nuevoPrestamo.setEstado("activo");

            PrestamoDAO prestamoDAO = new PrestamoDAO();
            prestamoDAO.insert(nuevoPrestamo);

            libroConsultado.setStock(libroConsultado.getStock() - 1);
            libroDAO.update(libroConsultado);

            llenadoDeTablas();

            // ✅ Mostrar fecha actual en txtFecha como referencia
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            txtFecha.setText(formato.format(ahora));

            JOptionPane.showMessageDialog(null, "Préstamo registrado correctamente.");

            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(idUsuario, APLICACION, "Registrar Préstamo");
        } else {
            JOptionPane.showMessageDialog(null, "No hay stock disponible para este libro.");
        }
    }

    public void buscarPrestamo() {
        prestamo prestamoConsultar = new prestamo();
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        prestamoConsultar.setId_prestamo(Integer.parseInt(txtbuscado.getText()));
        prestamoConsultar = prestamoDAO.query(prestamoConsultar);

        txtIdLibro.setText(Integer.toString(prestamoConsultar.getId_libro()));
        txtIdUsuario.setText(Integer.toString(prestamoConsultar.getId_usuario()));

        Timestamp fecha = prestamoConsultar.getFecha_prestamo();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        txtFecha.setText(formato.format(fecha));

        txtEstado.setText(prestamoConsultar.getEstado());

        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Buscar Préstamo");
    }

    public MantenimientoPrestamo() {
        initComponents();

        // ✅ Mostrar fecha actual al abrir el formulario
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        txtFecha.setText(formato.format(ahora));
        txtFecha.setEditable(false); // ✅ evitar edición manual

        llenadoDeTablas();

        idUsuarioSesion = UsuarioConectado.getIdUsuario();
        usuarioDAO = new UsuarioDAO();
        permisos = usuarioDAO.obtenerPermisosPorUsuario(idUsuarioSesion);

        btnEliminar.setEnabled(permisos.isPuedeEliminar());
        btnRegistrar.setEnabled(permisos.isPuedeRegistrar());
        btnModificar.setEnabled(permisos.isPuedeModificar());
    }

    @SuppressWarnings("unchecked")
    // aquí iría tu método initComponents generado por el diseñador de interfaces

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb2 = new javax.swing.JLabel();
        lbusu = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        label1 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        label3 = new javax.swing.JLabel();
        txtbuscado = new javax.swing.JTextField();
        txtIdLibro = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABLAP = new javax.swing.JTable();
        txtIdUsuario = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        label7 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        label10 = new javax.swing.JLabel();
        btnReporte = new javax.swing.JButton();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setBackground(new java.awt.Color(0, 153, 153));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("103");
        setVisible(true);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label1.setText("PRESTAMOS");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("LIBRO");

        txtIdLibro.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdLibro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        TABLAP.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TABLAP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Vendedor", "ID Empleado", "Correo", "Telefono", "Direccion", "Porcentaje", "Comision"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TABLAP);

        txtIdUsuario.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdUsuario.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("USUARIO");

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

        jButton1.setText("jButton1");

        jButton2.setText("Ayuda");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        label7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label7.setText("FECHA");

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtFecha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtFecha.setEnabled(false);
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        txtEstado.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtEstado.setText("activo");
        txtEstado.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtEstado.setEnabled(false);
        txtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoActionPerformed(evt);
            }
        });

        label10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label10.setText("ESTADO");

        btnReporte.setText("Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3)
                            .addComponent(label5)
                            .addComponent(label7)
                            .addComponent(label10))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                    .addComponent(txtIdLibro))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(label1)
                        .addGap(294, 294, 294))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(483, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(468, 468, 468))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtIdLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label3))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label5)))
                                    .addComponent(lb))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label7)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label10)
                                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnEliminar)
                                    .addComponent(btnRegistrar)
                                    .addComponent(btnModificar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLimpiar)
                                    .addComponent(btnBuscar)
                                    .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton2)
                                    .addComponent(btnReporte))
                                .addContainerGap(51, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 49, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addContainerGap(315, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//Boton eliminar
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
                                           
    try {
        // Validar que el campo no esté vacío
        String textoId = txtbuscado.getText().trim();
        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el ID del préstamo a eliminar.");
            return;
        }

        int idPrestamo = Integer.parseInt(textoId);

        // Verificar si el préstamo existe
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        if (!prestamoDAO.existePrestamo(idPrestamo)) {
            JOptionPane.showMessageDialog(null, "El préstamo con ID " + idPrestamo + " no existe.");
            return;
        }

        // Eliminar el préstamo
        prestamo prestamoAEliminar = new prestamo();
        prestamoAEliminar.setId_prestamo(idPrestamo);
        int resultado = prestamoDAO.delete(prestamoAEliminar);

        if (resultado > 0) {
            llenadoDeTablas();

            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Eliminar Préstamo");

            JOptionPane.showMessageDialog(null, "Préstamo eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el préstamo. Intente nuevamente.");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "ID inválido. Ingrese un número entero.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error al eliminar el préstamo: " + ex.getMessage());
        ex.printStackTrace(System.out);
    }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
                                           
    int idLibro = Integer.parseInt(txtIdLibro.getText());
    int idUsuario = Integer.parseInt(txtIdUsuario.getText());
    String estadoPrestamo = txtEstado.getText();

    LibroDAO libroDAO = new LibroDAO();
    libro libroConsultado = new libro();
    libroConsultado.setId_libro(idLibro);
    libroConsultado = libroDAO.query(libroConsultado);

    if (libroConsultado.getStock() > 0) {
        prestamo nuevoPrestamo = new prestamo();
        nuevoPrestamo.setId_libro(idLibro);
        nuevoPrestamo.setId_usuario(idUsuario);
        nuevoPrestamo.setEstado(estadoPrestamo);

        PrestamoDAO prestamoDAO = new PrestamoDAO();
        int resultado = prestamoDAO.insert(nuevoPrestamo); // ✅ validar resultado

        if (resultado > 0) {
            libroConsultado.setStock(libroConsultado.getStock() - 1);
            libroDAO.update(libroConsultado);

            llenadoDeTablas();

            UsuarioConectado usuarioEnSesion = new UsuarioConectado();
            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION, "Registrar Préstamo");

            JOptionPane.showMessageDialog(null, "Préstamo registrado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar el préstamo. Verifica los datos.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "No hay stock disponible para este libro.");
    }


    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
         buscarPrestamo();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
                                          
    try {
        // Validar que el campo de búsqueda no esté vacío
        String textoId = txtbuscado.getText().trim();
        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el ID del préstamo a modificar.");
            return;
        }

        int idPrestamo = Integer.parseInt(textoId);

        // Instanciar DAO y verificar existencia
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        if (!prestamoDAO.existePrestamo(idPrestamo)) {
            JOptionPane.showMessageDialog(null, "El préstamo con ID " + idPrestamo + " no existe.");
            return;
        }

        // Recuperar préstamo original
        prestamo prestamoExistente = new prestamo();
        prestamoExistente.setId_prestamo(idPrestamo);
        prestamoExistente = prestamoDAO.query(prestamoExistente);

        // Crear objeto actualizado
        prestamo prestamoAActualizar = new prestamo();
        prestamoAActualizar.setId_prestamo(idPrestamo);
        prestamoAActualizar.setId_libro(Integer.parseInt(txtIdLibro.getText()));
        prestamoAActualizar.setId_usuario(Integer.parseInt(txtIdUsuario.getText()));
        prestamoAActualizar.setEstado(txtEstado.getText());
        prestamoAActualizar.setFecha_prestamo(prestamoExistente.getFecha_prestamo()); // ✅ conservar fecha original

        // Ejecutar actualización
        int resultado = prestamoDAO.update(prestamoAActualizar);
        if (resultado > 0) {
            llenadoDeTablas();

            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Actualizar Préstamo");

            JOptionPane.showMessageDialog(null, "Préstamo actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el préstamo.");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "ID inválido. Ingrese un número entero.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error al actualizar el préstamo: " + ex.getMessage());
        ex.printStackTrace(System.out);
    }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed

    for (java.awt.Component comp : this.getContentPane().getComponents()) {
        if (comp instanceof javax.swing.JTextField) {
            javax.swing.JTextField campo = (javax.swing.JTextField) comp;
            if (campo != txtFecha && campo != txtEstado) { // ❌ no limpiar txtFecha ni txtEstado
                campo.setText("");
            }
        } else if (comp instanceof javax.swing.JComboBox) {
            ((javax.swing.JComboBox<?>) comp).setSelectedIndex(0);
        }
    }

    aplicarPermisos(permisosUsuarioActual);

    btnRegistrar.setEnabled(true);
    btnModificar.setEnabled(true);
    btnEliminar.setEnabled(true);

    System.out.println("Todos los campos han sido limpiados automáticamente (excepto fecha y estado).");

    UsuarioConectado usuarioEnSesion = new UsuarioConectado();
    Bitacora bitacoraRegistro = new Bitacora();
    bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION, "Limpiar Préstamo");

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if ((new File("src\\main\\java\\ayudas\\banco\\AyudaBanco.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\ayudas\\banco\\AyudaBanco.chm");
                p.waitFor();
            } else {
                System.out.println("La ayuda no Fue encontrada");
            }
            System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
         Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            connectio = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/reporte/bodegas/ReportePres.jrxml");
//
            print = JasperFillManager.fillReport(report, p, connectio);

            JasperViewer view = new JasperViewer(print, false);

            view.setTitle("Prueba reporte");
            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage());
        }
        
                                              
    }//GEN-LAST:event_btnReporteActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
                                               
    java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
    java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    txtFecha.setText(ahora.format(formato));


    }//GEN-LAST:event_txtFechaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TABLAP;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label10;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIdLibro;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtbuscado;
    // End of variables declaration//GEN-END:variables

    private void aplicarPermisos(permisos permisosUsuarioActual) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
