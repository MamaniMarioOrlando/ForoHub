package com.ForoHub.ForoHubChallenge.model.User;

import com.ForoHub.ForoHubChallenge.model.Course.CourseEntity;
import com.ForoHub.ForoHubChallenge.model.Response.ResponseEntidad;
import com.ForoHub.ForoHubChallenge.model.Topic.TopicEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "UserEntity")
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message="El nombre es obligatorio")
    private String username;
    @NotNull(message="El email no puede ser nulo")
    @Email(message= "Debe ser una direccion de correo electronico valido")
    private String email;
    @NotNull(message = "El campo clave no puede ser nulo")
    @Size(min = 6, max = 300, message = "La clave debe tener entre 6 y 300 caracteres")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<CourseEntity> courses;

    @OneToMany(mappedBy = "author")
    private List<ResponseEntidad> response;

    @OneToMany(mappedBy = "author")
    private List<TopicEntity> topics;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
