package com.alexis.proyecto.gestion.tareas.gestion_tareas.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.alexis.proyecto.gestion.tareas.gestion_tareas.models.Tarea;

@Repository
/**
 * Repositorio para la gestion de tareas en la base de datos MongoDB.
 * Proporciona metodos para realizar operaciones CRUD sobre la coleccion
 * "tareas".
 * 
 * @author Alex
 */
public interface TareaRepository extends MongoRepository<Tarea, ObjectId> {
    /**
     * Busca tareas por su nivel de prioridad.
     * 
     * @param prioridad String para buscar por prioridad (ejemplo: "Alta").
     * @return Lista de tareas coincidentes con la busqueda.
     */
    List<Tarea> findAllByPrioridad(String prioridad);

    /**
     * Busca un tarea por su titulo.
     * 
     * @param titulo titulo de la Tarea a buscar.
     * @return La Tarea del titulo buscado.
     */
    Optional<Tarea> findByTitulo(String titulo);
    /**
     * Busca la lista de tareas referente al usuario.
     * 
     * @param usuarioId Identificador del usuario.
     * @return Lista de tareas del usuario.
     */
    List<Tarea> findByUsuarioId(ObjectId usuarioId);
}
