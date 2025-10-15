package vista.biblioteca;

// MISHEL LOEIZA 9959-23-3457
// Mantenimiento para tabla devolucion
import Controlador.biblioteca.libro;
import Controlador.biblioteca.devolucion;
import Controlador.biblioteca.prestamo;
import Modelo.biblioteca.DevolucionDAO;
import Controlador.seguridad.UsuarioConectado;
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.permisos;
import Modelo.Conexion;
import Modelo.biblioteca.PrestamoDAO;
import Modelo.biblioteca.LibroDAO;
import Modelo.seguridad.UsuarioDAO;
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

public class MantenimientoDevolucion extends javax.swing.JInternalFrame {

   int APLICACION = 104;
    private Connection connectio;
    private int idUsuarioSesion;
    private UsuarioDAO usuarioDAO;
    private permisos permisos;
    private permisos permisosUsuarioActual;

    public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Devolución");
        modelo.addColumn("ID Préstamo");
        modelo.addColumn("Fecha Devolución");
        modelo.addColumn("Observaciones");

        DevolucionDAO devolucionDAO = new DevolucionDAO();
        List<devolucion> listaDevoluciones = devolucionDAO.select();
        TABLADEV.setModel(modelo);
        String[] dato = new String[4];

        for (devolucion d : listaDevoluciones) {
            dato[0] = Integer.toString(d.getId_devolucion());
            dato[1] = Integer.toString(d.getId_prestamo());

            Timestamp fecha = d.getFecha_devolucion();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dato[2] = formato.format(fecha);

            dato[3] = d.getObservaciones();
            modelo.addRow(dato);
        }
    }

public void registrarDevolucion() {
    int idPrestamo = Integer.parseInt(txtIdPrestamo.getText());
    String observaciones = txtObservaciones.getText();

    // 1️⃣ Registrar devolución
    devolucion nuevaDevolucion = new devolucion();
    nuevaDevolucion.setId_prestamo(idPrestamo);
    nuevaDevolucion.setObservaciones(observaciones);

    DevolucionDAO devolucionDAO = new DevolucionDAO();
    devolucionDAO.insert(nuevaDevolucion);

    // 2️⃣ Consultar préstamo original
    PrestamoDAO prestamoDAO = new PrestamoDAO();
    prestamo prestamoActual = new prestamo();
    prestamoActual.setId_prestamo(idPrestamo);
    prestamoActual = prestamoDAO.query(prestamoActual);

    // 3️⃣ Cambiar estado del préstamo a "inactivo"
    prestamoActual.setEstado("inactivo");
    prestamoDAO.update(prestamoActual); // Asegúrate de tener este método en PrestamoDAO

    // 4️⃣ Consultar libro y aumentar stock
    int idLibro = prestamoActual.getId_libro();
    LibroDAO libroDAO = new LibroDAO();
    libro libroActual = new libro();
    libroActual.setId_libro(idLibro);
    libroActual = libroDAO.query(libroActual);

    libroActual.setStock(libroActual.getStock() + 1);
    libroDAO.update(libroActual); // Asegúrate de tener este método en LibroDAO

    // 5️⃣ Actualizar tabla y mostrar fecha
    llenadoDeTablas();
    LocalDateTime ahora = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    txtFecha.setText(formato.format(ahora));

    JOptionPane.showMessageDialog(null, "Devolución registrada y préstamo actualizado.");

    // 6️⃣ Bitácora
    Bitacora bitacoraRegistro = new Bitacora();
    bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Registrar Devolución y actualizar préstamo");
}


    public void buscarDevolucion() {
        devolucion devolucionConsultar = new devolucion();
        devolucionConsultar.setId_devolucion(Integer.parseInt(txtBuscado.getText()));

        DevolucionDAO devolucionDAO = new DevolucionDAO();
        devolucionConsultar = devolucionDAO.query(devolucionConsultar);

        txtIdPrestamo.setText(Integer.toString(devolucionConsultar.getId_prestamo()));

        Timestamp fecha = devolucionConsultar.getFecha_devolucion();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        txtFecha.setText(formato.format(fecha));

        txtObservaciones.setText(devolucionConsultar.getObservaciones());

        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(idUsuarioSesion, APLICACION, "Buscar Devolución");
    }

    public MantenimientoDevolucion() {
        initComponents();

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        txtFecha.setText(formato.format(ahora));
        txtFecha.setEditable(false);

        llenadoDeTablas();

        idUsuarioSesion = UsuarioConectado.getIdUsuario();
    }

    @SuppressWarnings("unchecked")
    // Aquí iría tu método initComponents generado por el diseñador de interfaces

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb2 = new javax.swing.JLabel();
        lbusu = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        label1 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        txtBuscado = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABLADEV = new javax.swing.JTable();
        txtIdPrestamo = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        label7 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtObservaciones = new javax.swing.JTextField();
        label10 = new javax.swing.JLabel();
        btnReporte = new javax.swing.JButton();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setBackground(new java.awt.Color(0, 153, 153));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("104");
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
        label1.setText("DEVOLUCIONES");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        TABLADEV.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TABLADEV.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TABLADEV);

        txtIdPrestamo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdPrestamo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("ID PRESTAMO");

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

        txtObservaciones.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtObservaciones.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtObservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObservacionesActionPerformed(evt);
            }
        });

        label10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label10.setText("OBSERVACIONES");

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
                            .addComponent(label5)
                            .addComponent(label7)
                            .addComponent(label10))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(275, 275, 275)
                                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtIdPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                .addComponent(txtBuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
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
                                .addComponent(lb)
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIdPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label7)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label10)
                                    .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(96, 96, 96)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnEliminar)
                                    .addComponent(btnRegistrar)
                                    .addComponent(btnModificar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLimpiar)
                                    .addComponent(btnBuscar)
                                    .addComponent(txtBuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton2)
                                    .addComponent(btnReporte))
                                .addContainerGap(52, Short.MAX_VALUE))
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
        String textoId = txtBuscado.getText().trim();
        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el ID de la devolución a eliminar.");
            return;
        }

        int idDevolucion = Integer.parseInt(textoId);

        // Verificar si la devolución existe
        DevolucionDAO devolucionDAO = new DevolucionDAO();
        devolucion devolucionAEliminar = new devolucion();
        devolucionAEliminar.setId_devolucion(idDevolucion);
        devolucionAEliminar = devolucionDAO.query(devolucionAEliminar);

        if (devolucionAEliminar.getId_devolucion() == 0) {
            JOptionPane.showMessageDialog(null, "La devolución con ID " + idDevolucion + " no existe.");
            return;
        }

        // Eliminar devolución
        int resultado = devolucionDAO.delete(devolucionAEliminar); // Asegúrate de tener este método en tu DAO

        if (resultado > 0) {
            llenadoDeTablas();

            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Eliminar Devolución");

            JOptionPane.showMessageDialog(null, "Devolución eliminada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar la devolución. Intente nuevamente.");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "ID inválido. Ingrese un número entero.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error al eliminar la devolución: " + ex.getMessage());
        ex.printStackTrace(System.out);
    }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
                                            
    try {
        // 1️⃣ Validar campos
        String textoIdPrestamo = txtIdPrestamo.getText().trim();
        String observaciones = txtObservaciones.getText().trim();

        if (textoIdPrestamo.isEmpty() || observaciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos antes de registrar la devolución.");
            return;
        }

        int idPrestamo = Integer.parseInt(textoIdPrestamo);

        // 2️⃣ Consultar préstamo
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        prestamo prestamoActual = new prestamo();
        prestamoActual.setId_prestamo(idPrestamo);
        prestamoActual = prestamoDAO.query(prestamoActual);

        if (prestamoActual.getId_prestamo() == 0) {
            JOptionPane.showMessageDialog(null, "El préstamo con ID " + idPrestamo + " no existe.");
            return;
        }

        // 3️⃣ Validar que no esté ya devuelto
        if ("inactivo".equalsIgnoreCase(prestamoActual.getEstado())) {
            JOptionPane.showMessageDialog(null, "Este préstamo ya fue devuelto.");
            return;
        }

        // 4️⃣ Registrar devolución
        devolucion nuevaDevolucion = new devolucion();
        nuevaDevolucion.setId_prestamo(idPrestamo);
        nuevaDevolucion.setObservaciones(observaciones);

        DevolucionDAO devolucionDAO = new DevolucionDAO();
        int resultado = devolucionDAO.insert(nuevaDevolucion);

        if (resultado > 0) {
            // 5️⃣ Actualizar estado del préstamo
            prestamoActual.setEstado("inactivo");
            prestamoDAO.update(prestamoActual); // Asegúrate de tener este método

            // 6️⃣ Actualizar stock del libro
            int idLibro = prestamoActual.getId_libro();
            LibroDAO libroDAO = new LibroDAO();
            libro libroActual = new libro();
            libroActual.setId_libro(idLibro);
            libroActual = libroDAO.query(libroActual);

            libroActual.setStock(libroActual.getStock() + 1);
            libroDAO.update(libroActual);

            // 7️⃣ Actualizar tabla y bitácora
            llenadoDeTablas();

            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Registrar Devolución");

            JOptionPane.showMessageDialog(null, "Devolución registrada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar la devolución. Verifica los datos.");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "ID inválido. Ingrese un número entero.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage());
        ex.printStackTrace(System.out);
    }

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
         buscarDevolucion();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
                                            
    try {
        // 1️⃣ Validar campo de búsqueda
        String textoId = txtBuscado.getText().trim();
        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el ID de la devolución a modificar.");
            return;
        }

        int idDevolucion = Integer.parseInt(textoId);

        // 2️⃣ Verificar existencia de la devolución
        DevolucionDAO devolucionDAO = new DevolucionDAO();
        devolucion devolucionExistente = new devolucion();
        devolucionExistente.setId_devolucion(idDevolucion);
        devolucionExistente = devolucionDAO.query(devolucionExistente);

        if (devolucionExistente.getId_devolucion() == 0) {
            JOptionPane.showMessageDialog(null, "La devolución con ID " + idDevolucion + " no existe.");
            return;
        }

        // 3️⃣ Crear objeto actualizado
        devolucion devolucionActualizada = new devolucion();
        devolucionActualizada.setId_devolucion(idDevolucion);
        devolucionActualizada.setId_prestamo(Integer.parseInt(txtIdPrestamo.getText()));
        devolucionActualizada.setObservaciones(txtObservaciones.getText());
        devolucionActualizada.setFecha_devolucion(devolucionExistente.getFecha_devolucion()); // ✅ conservar fecha original

        // 4️⃣ Ejecutar actualización
        int resultado = devolucionDAO.update(devolucionActualizada); // ← Asegúrate de tener este método en tu DAO

        if (resultado > 0) {
            llenadoDeTablas();

            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Actualizar Devolución");

            JOptionPane.showMessageDialog(null, "Devolución actualizada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la devolución.");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "ID inválido. Ingrese un número entero.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error al actualizar la devolución: " + ex.getMessage());
        ex.printStackTrace(System.out);
    }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
                                         
    // 1️⃣ Limpiar campos del formulario (excepto fecha y observaciones)
    for (java.awt.Component comp : this.getContentPane().getComponents()) {
        if (comp instanceof javax.swing.JTextField) {
            javax.swing.JTextField campo = (javax.swing.JTextField) comp;
            if (campo != txtFecha && campo != txtObservaciones) { // ❌ no limpiar fecha ni observaciones
                campo.setText("");
            }
        } else if (comp instanceof javax.swing.JComboBox) {
            ((javax.swing.JComboBox<?>) comp).setSelectedIndex(0);
        }
    }

    // 2️⃣ Restaurar permisos y botones
    aplicarPermisos(permisosUsuarioActual);

    btnRegistrar.setEnabled(true);
    btnModificar.setEnabled(true);
    btnEliminar.setEnabled(true);

    // 3️⃣ Mensaje técnico en consola
    System.out.println("Todos los campos han sido limpiados automáticamente (excepto fecha y observaciones).");

    // 4️⃣ Registrar acción en bitácora
    Bitacora bitacoraRegistro = new Bitacora();
    bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Limpiar Devolución");

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

    private void txtObservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacionesActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
         Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            connectio = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/reporte/biblioteca/ReporteDev.jrxml");
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
    private javax.swing.JTable TABLADEV;
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
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JTextField txtBuscado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIdPrestamo;
    private javax.swing.JTextField txtObservaciones;
    // End of variables declaration//GEN-END:variables

    private void aplicarPermisos(permisos permisosUsuarioActual) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
