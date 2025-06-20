package ru.tihomirov.java_bot.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.stream.Collectors;


import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    private String username;
    private String password;
    private boolean expired;
    private boolean locked;
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = role.getAuthorities().stream()
                .map(auth -> (GrantedAuthority) () -> auth.getName())
                .collect(Collectors.toList());

        // Также добавляем роль с префиксом ROLE_
        authorities.add((GrantedAuthority) () -> "ROLE_" + role.getName());

        return authorities;
    }



    @Override public boolean isAccountNonExpired() { return !expired; }
    @Override public boolean isAccountNonLocked() { return !locked; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return enabled; }
}
