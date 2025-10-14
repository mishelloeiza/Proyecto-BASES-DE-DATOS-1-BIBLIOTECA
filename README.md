
## 📚 Proyecto: Sistema de Biblioteca con Oracle XE 21c BASES DE DATOS 1 - ALUMNA: MISHEL LOEIZA
Este sistema individual implementa una solución integral para la gestión de una biblioteca académica, 
utilizando **Oracle Database XE 21c** como motor principal y **Java Swing** como interfaz gráfica. 
Está diseñado para garantizar trazabilidad, control de acceso, y administración eficiente de recursos bibliográficos.

### 🔧 Módulos funcionales
#### 📖 Gestión de Libros
- Registro, modificación, consulta y eliminación de libros.
- Atributos: título, autor, editorial, categoría, stock y estado.
- 
#### 🔄 Préstamo y Devolución
- Registro de préstamos por usuario, con fecha de entrega y devolución.
- Validación de disponibilidad de stock.
- Control de sanciones por retraso (opcional).
- Actualización automática del estado del libro.

#### 👤 Mantenimiento de Usuarios
- Alta, modificación y baja de usuarios.
- Asignación de perfiles y permisos por aplicación.
- Validación de credenciales y control de acceso.

#### 🛡️ Mantenimiento de Perfiles
- Registro de perfiles como “Administrador”, “Consulta”, “Procesos”.
- Asociación de permisos por módulo: insertar, editar, eliminar, imprimir.
- Relación con usuarios mediante tabla `relperfusu`.

#### 📋 Bitácora de Acciones
- Registro automático de operaciones realizadas por cada usuario.
- Campos: fecha, IP, nombre de PC, acción, módulo.
- Consulta por rango de fechas y usuario.

### ⚙️ Arquitectura técnica

#### 🗃️ Base de datos Oracle XE 21c
- Motor relacional con soporte para SQL y PL/SQL.
- Uso de **tablespace personalizado** para segmentar el proyecto:

CREATE TABLESPACE PROYECTO_BDMISHEL
 DATAFILE 'PROYECTO_BDMISHEL_XEPDB1.dbf'
 SIZE 50M
 AUTOEXTEND ON
 NEXT 10M MAXSIZE UNLIMITED;

- Todas las tablas del sistema se crean dentro de este tablespace:

#### 🧾 Script ejecutable en SQL*Plus -- buscar en carpeta
- Script modular con bloques `CREATE TABLE`, `INSERT`, `ALTER`, `GRANT`.
- Incluye:
  - Creación de tablas: `usuario`, `libro`, `prestamo`, `devolucion`, `bitacora`, `aplicacion`, `perfiles`, `relusuapl`, `relperfusu`.
  - Inserción de datos base.
  - Asignación de permisos y claves foráneas.
  - Validación visual con `SELECT` al final de cada bloque.

### 🖥️ Interfaz gráfica (Java Swing)
- Ventanas internas (`JInternalFrame`) para cada módulo.
- Validación de permisos por usuario.
- Conexión JDBC con Oracle XE.
- Registro automático en bitácora tras cada acción.

