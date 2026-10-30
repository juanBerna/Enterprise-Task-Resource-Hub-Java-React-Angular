package backend_core.controller;

import backend_core.dto.OnboardingResourceDto;
import backend_core.service.OnboardingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/onboarding")
@Tag(name = "Onboarding Resources", description = "Endpoints para la gestión de guías técnicas y recursos de onboarding enterprise.")
public class OnboardingController {

    private final OnboardingService onboardingService;

    public OnboardingController(OnboardingService onboardingService) {
        this.onboardingService = onboardingService;
    }

    @Operation(summary = "Listar recursos de onboarding", description = "Retorna el catálogo completo de guías técnicas almacenadas en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<OnboardingResourceDto>> getAllResources() {
        return ResponseEntity.ok(onboardingService.getAllResources());
    }

    @Operation(summary = "Obtener recurso por ID", description = "Busca y retorna una guía técnica específica según su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OnboardingResourceDto> getResourceById(
            @PathVariable @Parameter(description = "ID único del recurso de onboarding") Long id) {
        return ResponseEntity.ok(onboardingService.getResourceById(id));
    }

    @Operation(summary = "Crear un nuevo recurso de onboarding", description = "Registra y persiste una nueva guía técnica en la base de datos relacional. Requiere autenticación JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recurso creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o faltantes"),
            @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT ausente o inválido")
    })
    @SecurityRequirement(name = "bearerAuth") // <--- Indica a Swagger que este endpoint pide candado
    @PostMapping
    public ResponseEntity<OnboardingResourceDto> createResource(
            @Valid @RequestBody @Parameter(description = "Datos del recurso a registrar") OnboardingResourceDto dto) {

        OnboardingResourceDto createdResource = onboardingService.createResource(dto);
        return new ResponseEntity<>(createdResource, HttpStatus.CREATED);
    }
}