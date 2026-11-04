package backend_core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO que representa un registro del usuario.")
public class RegisterRequestDto {
    @NotBlank(message = "El Nombre del Usuario es obligatorio")
    @Size(min = 3, max = 50, message = "El título debe tener entre 3 y 50 caracteres")
    @Schema(description = "Título descriptivo del recurso técnico", example = "Juan Perez Perez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank(message = "El Email es obligatorio")
    @Size(min = 3, max = 50, message = "El título debe tener entre 3 y 50 caracteres")
    @Schema(description = "Título descriptivo del recurso técnico", example = "correo@test.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "La Contraseña es obligatorio")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    @Schema(description = "Título descriptivo del recurso técnico", example = "123456#", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    public RegisterRequestDto(){}

    public  RegisterRequestDto(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;

    }
}
