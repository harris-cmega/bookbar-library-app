package io.bookbar.bookbarbackend.entity;

import io.bookbar.bookbarbackend.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;


    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    @Column(nullable = false)
    private String apiKey;

    public User(Long id, String username, String email, String password, UserRole role) {
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
