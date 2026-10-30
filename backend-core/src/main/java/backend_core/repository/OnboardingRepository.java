package backend_core.repository;

import backend_core.model.OnboardingResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingRepository extends JpaRepository<OnboardingResourceEntity, Long> {
    // Spring Data JPA provee automáticamente métodos CRUD y hereda la ejecución asíncrona sobre Virtual Threads
}