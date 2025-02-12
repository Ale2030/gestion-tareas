package com.alexis.proyecto.gestion.tareas.gestion_tareas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.alexis.proyecto.gestion.tareas.gestion_tareas.models.Usuario;
import com.alexis.proyecto.gestion.tareas.gestion_tareas.service.serviceImpl.UsuarioServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
/**
 * Controlador para la autenticación de usuarios.
 * 
 * @author Alex
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
    private final AuthenticationManager authenticationManager;
     /**
     * Constructor de la clase AuthController.
     *
     * @param authenticationManager Gestor de autenticación de Spring Security.
     */
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
     /**
     * Maneja el inicio de sesión de un usuario.
     *
     * @param credentials Mapa que contiene las credenciales del usuario (username y password).
     * @param httpRequest Objeto HttpServletRequest para manejar la sesión.
     * @return ResponseEntity con información del usuario logueado o un mensaje de error si las credenciales son inválidas.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials, HttpServletRequest httpRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password")));

            SecurityContextHolder.getContext().setAuthentication(auth);

            HttpSession session = httpRequest.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            Usuario usuario = usuarioServiceImpl.getByUsername(credentials.get("username"));
            session.setAttribute("usuario", usuario);
            return ResponseEntity.ok(Map.of(
                    "message", "Login exitoso",
                    "username", usuario.getUsername(),
                    "sessionId", session.getId()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales inválidas"));
        }
    }
     /**
     * Maneja el cierre de sesión (logout) de un usuario.
     *
     * @param request  Objeto HttpServletRequest para acceder a la sesión actual.
     * @param response Objeto HttpServletResponse para personalizar la respuesta.
     * @return ResponseEntity indicando que el logout fue exitoso.
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok(Map.of("message", "Logout exitoso"));
    }

}
