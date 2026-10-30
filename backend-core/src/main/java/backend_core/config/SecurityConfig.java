package backend_core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitar CSRF ya que nuestra API es RESTful y stateless (usa tokens JWT)
                .csrf(csrf -> csrf.disable())

                // Configurar política de sesiones como STATELESS (sin sesiones en servidor)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Reglas de autorización de endpoints
                .authorizeHttpRequests(auth -> auth
                        // Documentación de OpenAPI / Swagger UI pública para facilitar pruebas y desarrollo
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()

                        // Endpoints públicos de lectura (GET)
                        .requestMatchers(HttpMethod.GET, "/api/v1/onboarding/**").permitAll()

                        // Endpoints de escritura o administración protegidos (requieren autenticación JWT)
                        .requestMatchers(HttpMethod.POST, "/api/v1/onboarding/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/onboarding/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/onboarding/**").authenticated()

                        // Cualquier otra petición requiere autenticación por defecto
                        .anyRequest().authenticated()
                )

                // Habilitar el servidor de recursos OAuth2 validando tokens JWT
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}));

        return http.build();
    }
}