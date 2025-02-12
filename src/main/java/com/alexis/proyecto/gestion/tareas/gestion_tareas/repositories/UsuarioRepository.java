package com.alexis.proyecto.gestion.tareas.gestion_tareas.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.alexis.proyecto.gestion.tareas.gestion_tareas.models.Usuario;

@Repository
/**
 * Repositorio para la gestion de usuarios en la base de datos MongoDB.
 * Proporciona metodos para realizar operaciones CRUD sobre la coleccion
 * "usuarios".
 * 
 * @author Alex
 */
public interface UsuarioRepository extends MongoRepository<Usuario,ObjectId> {
    /**
     * Busca un usuario por su nombre.
     * 
     * @param nombre Nombre de usuario a buscar.
     * @return  El usuario buscado.
     */
    Optional<Usuario> findByUsername(String username);
}
