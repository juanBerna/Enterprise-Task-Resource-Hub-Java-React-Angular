package backend_core.controller;

import backend_core.dto.OnboardingResourceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/onboarding")
@Tag(name = "Onboarding Resources", description = "Endpoints para la gestión de guías técnicas y recursos de onboarding enterprise.")
public class OnboardingController {

    // Simulación temporal en memoria para validar el contrato (se conectará a JPA en la Fase 2)
    private final List<OnboardingResourceDto> resourceDatabase = new ArrayList<>();

    @Operation(summary = "Listar recursos de onboarding", description = "Retorna el catálogo completo de guías técnicas y recursos disponibles para los nuevos desarrolladores.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<OnboardingResourceDto>> getAllResources() {
        return ResponseEntity.ok(resourceDatabase);
    }

    @Operation(summary = "Crear un nuevo recurso de onboarding", description = "Registra una nueva guía técnica validando los datos de entrada según el contrato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recurso creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o faltantes")
    })
    @PostMapping
    public ResponseEntity<OnboardingResourceDto> createResource(
            @Valid @RequestBody @Parameter(description = "Datos del recurso a registrar") OnboardingResourceDto dto) {

        // Simulamos un ID autoincremental para el ejercicio de contrato
        long nextId = resourceDatabase.size() + 1;
        dto.setId(nextId);
        resourceDatabase.add(dto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}