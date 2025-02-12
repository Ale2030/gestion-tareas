package com.alexis.proyecto.gestion.tareas.gestion_tareas.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.proyecto.gestion.tareas.gestion_tareas.models.Tarea;
import com.alexis.proyecto.gestion.tareas.gestion_tareas.models.Usuario;
import com.alexis.proyecto.gestion.tareas.gestion_tareas.repositories.UsuarioRepository;
import com.alexis.proyecto.gestion.tareas.gestion_tareas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TareaServiceImpl tareaServiceImpl;

    @Override
    public List<Tarea> getDatosUsuario(String nombre) {
        List<Tarea> tareas = tareaServiceImpl.getAllTareasByUsuario(nombre);
        return tareas;
    }

    @Override
    public Usuario getByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("El usuario no puede ser nulo"));
        return usuario;
    }
    

}
