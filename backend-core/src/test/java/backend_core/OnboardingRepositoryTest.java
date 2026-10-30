package backend_core;

import backend_core.model.OnboardingResourceEntity;
import backend_core.repository.OnboardingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// NUEVOS IMPORTS OFICIALES PARA SPRING BOOT 4:
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OnboardingRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private OnboardingRepository onboardingRepository;

    @Test
    void shouldSaveAndFindResourceSuccessfully() {
        OnboardingResourceEntity entity = new OnboardingResourceEntity(
                "Testcontainers con PostgreSQL",
                "Prueba de integración automatizada usando contenedores Docker reales.",
                "Java / Docker"
        );

        OnboardingResourceEntity savedEntity = onboardingRepository.save(entity);

        assertThat(savedEntity).isNotNull();
        assertThat(savedEntity.getId()).isNotNull();
        assertThat(savedEntity.getTitle()).isEqualTo("Testcontainers con PostgreSQL");
    }
}