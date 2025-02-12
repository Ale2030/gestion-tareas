package com.alexis.proyecto.gestion.tareas.gestion_tareas.controllers;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexis.proyecto.gestion.tareas.gestion_tareas.models.Tarea;
import com.alexis.proyecto.gestion.tareas.gestion_tareas.models.Usuario;
import com.alexis.proyecto.gestion.tareas.gestion_tareas.service.serviceImpl.TareaServiceImpl;
import com.alexis.proyecto.gestion.tareas.gestion_tareas.service.serviceImpl.UsuarioServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * 
 * Controlador para manejar las operaciones relacionadas con los usuarios
 * y sus tareas en la aplicación de gestión de tareas.
 * 
 * @author Alex
 */
@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    @Autowired
    UsuarioServiceImpl usuarioServiceImpl;
    @Autowired
    TareaServiceImpl tareaServiceImpl;

    /**
     * Obtiene las tareas asociadas a un usuario específico.
     *
     * @param nombre  el nombre del usuario
     * @param request la solicitud HTTP
     * @return una respuesta que contiene la lista de tareas del usuario
     */
    @GetMapping("/{nombre}")
    public ResponseEntity<?> getTareasUsuario(@PathVariable String nombre, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(403).body("No hay sesión activa");
        }
        // Recupera el objeto Usuario desde la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !usuario.getUsername().equals(nombre)) {
            return ResponseEntity.status(403).body("No autorizado");
        }
        List<Tarea> tareas = usuarioServiceImpl.getDatosUsuario(nombre);
        return ResponseEntity.ok().body(tareas);
    }

    /**
     * Obtiene todas las tareas filtradas por una propiedad de prioridad.
     *
     * @param propiedad la propiedad de prioridad
     * @param request   la solicitud HTTP
     * @return una respuesta que contiene la lista de tareas filtradas
     */
    @GetMapping("/prioridad/{propiedad}")
    public ResponseEntity<?> getForPropiedad(@PathVariable String propiedad, HttpServletRequest request) {
        try {
            System.out.println("Buscando prioridad: " + propiedad);
            List<Tarea> tareas = tareaServiceImpl.getAllTareaByPrioridad(propiedad);
            return ResponseEntity.ok(tareas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Crea una nueva tarea asociada al usuario autenticado.
     *
     * @param tarea   la tarea a crear
     * @param request la solicitud HTTP
     * @return una respuesta que contiene la tarea creada
     */
    @PostMapping()
    public ResponseEntity<?> postTarea(@RequestBody Tarea tarea, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                return ResponseEntity.status(403).body("No hay sesión activa");
            }
            String sessionUsername = (String) session.getAttribute("username");
            if (sessionUsername == null) {
                return ResponseEntity.status(403).body("Usuario no autenticado");
            }
            Usuario usuario = usuarioServiceImpl.getByUsername(sessionUsername);
            if (usuario == null) {
                return ResponseEntity.status(403).body("Usuario no encontrado");
            }
            tarea.setUsuarioId(usuario.getUsuarioId());
            tarea.setFecha_creacion(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            tareaServiceImpl.createTarea(tarea);
            return ResponseEntity.ok(tarea);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Actualiza una tarea existente.
     *
     * @param idTarea el ID de la tarea a actualizar
     * @param tarea   los datos actualizados de la tarea
     * @return una respuesta que contiene la tarea actualizada
     */
    @PatchMapping("/{idTarea}")
    public ResponseEntity<?> patchTarea(@PathVariable String idTarea, @RequestBody Tarea tarea) {
        try {
            ObjectId objectId = new ObjectId(idTarea);
            tareaServiceImpl.updateTarea(objectId, tarea);
            return ResponseEntity.ok(tarea);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Elimina una tarea existente.
     *
     * @param idTarea el ID de la tarea a eliminar
     * @return una respuesta sin contenido
     */
    @DeleteMapping("/{idTarea}")
    public ResponseEntity<?> deleteTarea(@PathVariable String idTarea) {
        try {
            ObjectId objectId = new ObjectId(idTarea);
            tareaServiceImpl.deleteTarea(objectId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
