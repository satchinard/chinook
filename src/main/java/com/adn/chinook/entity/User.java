package com.adn.chinook.entity;

import com.adn.chinook.base.BaseEntite;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author cagecfi
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@Entity
@Table(name = "UTILISATEUR")
public class User extends BaseEntite implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(unique = true)
    @NotNull(message = "Login non fourni.")
    @Size(min = 8, message = "Taille du login incorrecte (inférieure à 8 caractères).")
    private String username;
    @NotBlank(message = "Mot de passe vide.")
    @NotNull(message = "Mot de passe non fourni.")
    private String password;
    private String firstName;
    private String lastName;
    @Email(message = "Email incorrect.")
    @NotNull(message = "Email non fourni.")
    @Column(unique = true)
    private String email;
    private Boolean enabled = false;
    private Boolean accountNonExpired = false;
    private Boolean accountNonLocked = false;
    private Boolean credentialsNonExpired = false;
    private Boolean tokenExpired = false;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "roleId"))
    @Fetch(FetchMode.SELECT)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
