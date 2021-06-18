package com.adn.chinook.entity;

import com.adn.chinook.base.BaseEntite;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author cagecfi
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "PRIVILEGE")
public class Privilege extends BaseEntite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long privilegeId;
    @NotNull(message = "Le nom ne peut être vide.")
    @NotBlank(message = "Le nom est invalide.")
    private String name;
    @ManyToMany(mappedBy = "privileges")
    @Fetch(FetchMode.SELECT)
    private Set<Role> roles;

    public Privilege(String name) {
        this.name = name;
    }

}
