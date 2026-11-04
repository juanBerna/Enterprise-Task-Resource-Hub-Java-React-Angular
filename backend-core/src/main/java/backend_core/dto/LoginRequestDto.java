package backend_core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa un rrequest del login.")
public class LoginRequestDto {
    @NotBlank(message = "El Usuario es obligatorio")
    @Size(min = 3, max = 50, message = "El título debe tener entre 3 y 50 caracteres")
    @Schema(description = "Título descriptivo del recurso técnico", example = "Arquitectura de Virtual Threads en Java 25", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank(message = "La Contraseña es obligatoria")
    @Size(min = 3, max = 50, message = "El título debe tener entre 3 y 50 caracteres")
    @Schema(description = "Título descriptivo del recurso técnico", example = "Arquitectura de Virtual Threads en Java 25", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
