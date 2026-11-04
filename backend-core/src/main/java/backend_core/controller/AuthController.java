package backend_core.controller;

import backend_core.dto.LoginRequestDto;
import backend_core.dto.RegisterRequestDto;
import backend_core.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000") // Permite peticiones desde Next.js
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto request){
        String response = authService.register(request);
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto request) {
        String token = authService.login(request);
        // Retornamos el token JWT en formato texto plano (o podríamos envolverlo en un DTO)
        return ResponseEntity.ok(token);
    }
}
