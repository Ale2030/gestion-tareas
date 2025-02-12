package com.alexis.proyecto.gestion.tareas.gestion_tareas.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alexis.proyecto.gestion.tareas.gestion_tareas.models.Usuario;
import com.alexis.proyecto.gestion.tareas.gestion_tareas.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
/**
 * Servicio personalizado para cargar los detalles de un usuario desde la base
 * de datos.
 * 
 * @author Alex
 */
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
        return User.builder()
        .username(usuario.getUsername())
        .password(usuario.getContrasena())
        .build();
    }
    
}
