package com.alexis.proyecto.gestion.tareas.gestion_tareas.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuarios")
/**
 * Clase que representa a un usuario dentro del sistema de gestion de tareas.
 * Esta clase esta mapeada a la collection "usuarios".
 * 
 * @author Alex
 */
public class Usuario {
    /**
     * Identificador del usuario.
     */
    @Field("_id")
    private ObjectId usuarioId;

    /**
     * Nombre de usuario.
     */
    private String username;
    /**
     * Email del usuario.
     */
    private String email;
    /**
     * Contraseña del usuario.
     */
    private String contrasena;
}
