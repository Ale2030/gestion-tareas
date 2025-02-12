package com.alexis.proyecto.gestion.tareas.gestion_tareas.models;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tareas")
/**
 * Representa una tarea dentro del sistema de gestion de tareas.
 * Esta clase esta mapeada a la collection "tareas".
 * 
 * @author Alex
 * 
 */
public class Tarea {
  
  /**
   * Identificador único de la tarea.
   */
  @Id
  @Field("_id")
  @JsonSerialize(using = ToStringSerializer.class)
  private ObjectId id;
  /**
   * Identificador del usuario al que pertenece la tarea.
   */
  @Field("usuario_id")
  private ObjectId usuarioId;
  /**
   * Titulo de la tarea.
   */
  private String titulo;
  /**
   * Descripcion de la tarea
   */
  private String descripcion;
  /**
   * Estado actual de la tarea (ejemplo:"Pendiente").
   */
  private String estado;
  /**
   * Fecha de la tarea creada.
   */
  private Date fecha_creacion;
  /**
   * Fecha limite para terminar la tarea.
   */
  private Date fecha_vencimiento;
  /**
   * Nivel de prioridad (Ejemplo:"Alta").
   */
  private String prioridad;
  /**
   * Lista de etiquetas asociadas a la tarea.
   */
  private List<String> etiquetas;
}
