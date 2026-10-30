package backend_core.service;

import backend_core.dto.OnboardingResourceDto;
import backend_core.exception.ResourceNotFoundException;
import backend_core.model.OnboardingResourceEntity;
import backend_core.repository.OnboardingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OnboardingService {

    private final OnboardingRepository onboardingRepository;

    // Inyección de dependencias por constructor (Buena práctica recomendada en Spring Moderno)
    public OnboardingService(OnboardingRepository onboardingRepository) {
        this.onboardingRepository = onboardingRepository;
    }

    @Transactional(readOnly = true)
    public List<OnboardingResourceDto> getAllResources() {
        return onboardingRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public OnboardingResourceDto createResource(OnboardingResourceDto dto) {
        // 1. Mapear DTO de entrada a Entidad JPA
        OnboardingResourceEntity entity = new OnboardingResourceEntity(
                dto.getTitle(),
                dto.getDescription(),
                dto.getTechnology()
        );

        // 2. Persistir en la base de datos (H2 / PostgreSQL)
        OnboardingResourceEntity savedEntity = onboardingRepository.save(entity);

        // 3. Retornar DTO de respuesta con el ID generado
        return mapToDto(savedEntity);
    }

    // Método utilitario de mapeo Entidad <-> DTO
    private OnboardingResourceDto mapToDto(OnboardingResourceEntity entity) {
        return new OnboardingResourceDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getTechnology()
        );
    }

    public OnboardingResourceDto getResourceById(Long id) {
        OnboardingResourceEntity entity = onboardingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso de onboarding no encontrado con ID: " + id));
        return mapToDto(entity);
    }
}