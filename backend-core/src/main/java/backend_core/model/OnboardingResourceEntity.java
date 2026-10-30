package backend_core.model;

import jakarta.persistence.*;

@Entity
@Table(name = "onboarding_resources")
public class OnboardingResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, length = 50)
    private String technology;

    // Constructors
    public OnboardingResourceEntity() {}

    public OnboardingResourceEntity(String title, String description, String technology) {
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