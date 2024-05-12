package preproject.PP_31.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Role() {
    }

    public Role(String name) {
        switch (name) {
            case "ROLE_USER" -> {
                this.name = name;
                this.id = 1L;
            }
            case "ROLE_ADMIN" -> {
                this.name = name;
                this.id = 2L;
            }
        }
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.replaceAll("ROLE_", "");
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

}
