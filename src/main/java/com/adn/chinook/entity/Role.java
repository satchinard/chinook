package com.adn.chinook.entity;

import com.adn.chinook.base.BaseEntite;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author cagecfi
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;
    @NotNull(message = "Le nom n'est pas valide.'")
    private String name;
    @ManyToMany(mappedBy = "roles")
    @Fetch(FetchMode.SELECT)
    private Set<User> users;
    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "privilegeId"))
    @Fetch(FetchMode.SELECT)
    private Set<Privilege> privileges;

    public Role(String name) {
        this.name = name;
    }

}
