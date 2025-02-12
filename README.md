# 📝 API Gestión de Tareas - Task Manager 🚀

Este proyecto es una API RESTful desarrollada en Spring Boot, que permite la gestión eficiente de tareas dentro de un sistema. Incluye funcionalidades para crear, leer, actualizar y eliminar tareas, asignarlas a usuarios y establecer prioridades.

---

## 🚀 Características
- **Gestión de Tareas**: Crear, actualizar, eliminar y consultar tareas.
- **Gestión de Usuarios**: Registrar usuarios y asignarles tareas.
- **Autenticación y Seguridad**:  Implementación con Spring Security para gestionar accesos y permisos.
- **Prioridades y Estados**: Organización de tareas según su prioridad y estado (pendiente, en proceso, completada).

---

## 🗂️ Estructura del Proyecto
### Entidades Principales
1. **Tarea**
   - `id`: Identificador único.
   - `titulo`: Título de la tarea.
   - `descripcion`: Detalles de la tarea.
   - `fechaCreacion`: Fecha en la que se creó la tarea.
   - `fechaVencimiento`: Fecha límite de la tarea.
   - `prioridad`: Nivel de prioridad (baja,media,alta).
   - `estado`: Estado actual (pendiente,en proceso, completada).
   - `idUsuario`: Relación con el usuario asignado.

2. **Usuario**
   - `idUsuario`: Identificador único.
   - `username`: Nombre de usuario.
   - `email`: Mail del usuario.
   - `contrasena`: Contraseña del usuario.

---

## 🔧 Tecnologías Utilizadas
- **Java 21**
- **Spring Boot**
- **Spring Security**
- **MongoDB** (Base de datos NoSQL)
- **Angular** (Frontend)

---

## ⚙️ Instalación y Uso

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Ale2030/gestion-tareas.git
   cd gestion-tareas
