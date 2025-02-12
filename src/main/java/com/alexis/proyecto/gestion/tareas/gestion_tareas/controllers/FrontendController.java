package com.alexis.proyecto.gestion.tareas.gestion_tareas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador para manejar las rutas del frontend.
 * Este controlador redirige todas las rutas no específicas del backend a la
 * página principal del frontend (`index.html`).
 * 
 * @author Alex
 */
@Controller
public class FrontendController {
    @RequestMapping(value = { "/{path:[^\\.]*}", "/**/{path:[^\\.]*}" })
    public String forward() {
        return "forward:/index.html";
    }
}
