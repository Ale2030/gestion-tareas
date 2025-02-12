package com.alexis.proyecto.gestion.tareas.gestion_tareas.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.alexis.proyecto.gestion.tareas.gestion_tareas.models.Tarea;

/**
 * Servicio para la gesrtion de tareas del sistema
 * Define las operaciones CRUD sobre la entidad{@link Tarea}
 */
public interface TareaService {
    /**
     * Obtiene todas las Tareas del sistema.
     * 
     * @return Lista de tareas.
     */
    List<Tarea> getAllTareas();
    /**
     * Obtiene la tarea por su ID.
     * @param idTarea Identificador de la tarea.
     * @return Tarea buscada.
     */
    Tarea getTareaById(ObjectId idTarea);
    /**
     * Busca una tarea por nivel de prioridad.
     * 
     * @param prioridad prioridad Prioridad de la tarea a buscar (ejemplo:
     *                  {@code Prioridad.Alta}).
     * @return Una lista de las  tareas encontradas.
     */
    List<Tarea> getAllTareaByPrioridad(String prioridad);

    /**
     * Obtiene una lista de tares por el usuario
     * 
     * @param nombreUsuario Nombre del usuario a buscar. 
     * @return Lista de tareas del usuario.
     */
    List<Tarea> getAllTareasByUsuario(String nombreUsuario);
    /**
     * Crea una instancia de Tarea.
     * 
     * @param tarea Objeto {@link Tarea} que se desea guardar.
     * @return La tarea creada.
     */
    Tarea createTarea(Tarea tarea);

    /**
     * Actualiza una Tarea existente.
     * 
     * @param tarea Objeto {@link Tarea} con los nuevos datos.
     * @param idTarea Identificador del objeto a actualizar.
     * @return La tarea actualizada.
     */
    Tarea updateTarea(ObjectId idTarea,Tarea tarea);

    /**
     * Elimina una tarea existente.
     * 
     * @param idtarea id de la tarea que se desea eliminar.
     */
    void deleteTarea(ObjectId idTarea);

}
