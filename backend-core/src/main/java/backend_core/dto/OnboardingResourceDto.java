package backend_core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO que representa un recurso técnico o guía de onboarding en el sistema.")
public class OnboardingResourceDto {

    @Schema(description = "Identificador único del recurso", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    @Schema(description = "Título descriptivo del recurso técnico", example = "Arquitectura de Virtual Threads en Java 25", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @NotBlank(message = "La descripción es obligatoria")
    @Schema(description = "Detalle o resumen técnico del contenido", example = "Guía completa sobre cómo habilitar y optimizar la concurrencia masiva con Virtual Threads.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(description = "Tecnología o módulo asociado", example = "Java / Spring Boot", requiredMode = Schema.RequiredMode.REQUIRED)
    private String technology;

    // Constructors
    public OnboardingResourceDto() {}

    public OnboardingResourceDto(Long id, String title, String description, String technology) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.technology = technology;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTechnology() { return technology; }
    public void setTechnology(String technology) { this.technology = technology; }
}