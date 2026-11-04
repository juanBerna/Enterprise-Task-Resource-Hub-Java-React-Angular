package backend_core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private String password; // Almacenará el hash BCrypt

    @Column(nullable = false)
    private String role = "ROLE_USER";

    public UserEntity(){}

    public UserEntity(String username, String email, String password, String role){
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = (role!= null)? role: "ROLE_USER";
    }

}
