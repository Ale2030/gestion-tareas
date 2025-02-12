package com.alexis.proyecto.gestion.tareas.gestion_tareas.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.proyecto.gestion.tareas.gestion_tareas.models.Tarea;
import com.alexis.proyecto.gestion.tareas.gestion_tareas.models.Usuario;
import com.alexis.proyecto.gestion.tareas.gestion_tareas.repositories.TareaRepository;
import com.alexis.proyecto.gestion.tareas.gestion_tareas.repositories.UsuarioRepository;
import com.alexis.proyecto.gestion.tareas.gestion_tareas.service.TareaService;

/**
 * Clase de servicio para tarea
 * Gestiona la logica de negocio de tarea
 */
@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Tarea createTarea(Tarea tarea) {
        Optional.ofNullable(tarea)
                .orElseThrow(() -> new IllegalArgumentException("La tarea no puede ser nula"));
        return tareaRepository.save(tarea);
    }

    @Override
    public void deleteTarea(ObjectId idTarea) {
        tareaRepository.deleteById(idTarea);
    }

    @Override
    public List<Tarea> getAllTareaByPrioridad(String prioridad) {
        List<Tarea> tareas = tareaRepository.findAllByPrioridad(prioridad);
        if (tareas.isEmpty()) {
            throw new IllegalArgumentException("Tareas no encontradas con la prioridad especificada");
        }
        return tareas;
    }

    @Override
    public List<Tarea> getAllTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Tarea updateTarea(ObjectId idTarea, Tarea newTarea) {
        Tarea changeTarea = tareaRepository.findById(idTarea).get();
        changeTarea.setTitulo(newTarea.getTitulo());
        changeTarea.setDescripcion(newTarea.getDescripcion());
        changeTarea.setEstado(newTarea.getEstado());
        changeTarea.setEtiquetas(newTarea.getEtiquetas());
        changeTarea.setFecha_creacion(newTarea.getFecha_creacion());
        changeTarea.setFecha_vencimiento(newTarea.getFecha_vencimiento());
        return tareaRepository.save(changeTarea);
    }

    @Override
    public List<Tarea> getAllTareasByUsuario(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        List<Tarea> tareas = tareaRepository.findByUsuarioId(usuario.getUsuarioId());
        return tareas;
    }

    @Override
    public Tarea getTareaById(ObjectId idTarea) {
        Tarea tarea = tareaRepository.findById(idTarea).orElseThrow(() -> new IllegalArgumentException("La tarea no puede ser nula"));
        return tarea;
    }

}
