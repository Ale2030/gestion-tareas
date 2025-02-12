package com.alexis.proyecto.gestion.tareas.gestion_tareas.service;

import java.util.List;

import com.alexis.proyecto.gestion.tareas.gestion_tareas.models.Tarea;
import com.alexis.proyecto.gestion.tareas.gestion_tareas.models.Usuario;

/**
 * 
 * Servicio para la gestion de usuarios y sus tareas
 */
public interface UsuarioService {
    /**
     * Obtiene una lista de tareas asociadas a un usuario ingresado.
     * 
     * @param nombre nombre del usuario.
     * @return Lista referente al usuario buscado.
     */
    List<Tarea> getDatosUsuario(String nombre);
    /**
     * Obtiene un Usuario por su username
     * @param username nombre de usuario a buscar
     * @return Usuario buscado por username
     */
    Usuario getByUsername(String username);
}
