package com.alexis.proyecto.gestion.tareas.gestion_tareas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Configuración de seguridad para la aplicación.
 * Esta clase define cómo se gestionan la autenticación, el control de acceso,
 * el manejo de sesiones y las configuraciones de CORS.
 * 
 * @author Alex
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
        /**
         * Configuración de CORS (Cross-Origin Resource Sharing).
         * Permite que el frontend (Angular) interactúe con el backend sin restricciones
         * de origen.
         * 
         * @return Una instancia de {@link CorsConfigurationSource} con las reglas
         *         configuradas.
         */
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("http://localhost:4200", "http://localhost:8083"));
                config.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE", "OPTIONS"));
                config.setAllowedHeaders(List.of("*"));
                config.setAllowCredentials(true);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", config);
                return source;
        }

        /**
         * Configura el AuthenticationManager, que es el componente principal
         * para gestionar la autenticación.
         * 
         * @param authenticationConfiguration
         * @return Una instancia de {@link AuthenticationManager}.
         * @throws Exception Si ocurre un error al crear el
         *                   {@link AuthenticationManager}.
         */
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        /**
         * Define el repositorio de contexto de seguridad que almacena la información de
         * autenticación en la sesión HTTP.
         * 
         * @return Una instancia de {@link SecurityContextRepository} basada en
         *         sesiones.
         */
        @Bean
        public SecurityContextRepository securityContextRepository() {
                return new HttpSessionSecurityContextRepository();
        }

        /**
         * Configuración principal de la seguridad HTTP.
         * Define las reglas de acceso a las rutas, el manejo de sesiones, CORS, CSRF,
         * entre otros.
         * 
         * @param http El objeto {@link HttpSecurity} para configurar la seguridad.
         * @return Una instancia de {@link SecurityFilterChain} con la configuración aplicada.
         * @throws Exception Si ocurre un error al construir la configuración.
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .formLogin(form -> form.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/", "/index.html", "/assets/**",
                                                                "/favicon.svg",
                                                                "/runtime.*", "/polyfills.*", "/main.*", "/styles.*")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/auth/logout").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/v1/usuario/prioridad/**")
                                                .authenticated()
                                                .anyRequest().permitAll())
                                .securityContext(securityContext -> securityContext
                                                .securityContextRepository(securityContextRepository()))
                                .cors(cors -> cors.configurationSource(corsConfigurationSource())) 
                                .csrf(csrf -> csrf.disable()) 
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                                .logout(logout -> logout
                                                .disable());
                return http.build();
        }

        /**
          * Bean para la encriptación de contraseñas.
         * 
         * @return Una instancia de {@link PasswordEncoder}.
         */
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
