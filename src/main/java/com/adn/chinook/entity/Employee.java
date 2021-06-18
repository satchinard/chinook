package com.adn.chinook.entity;

import com.adn.chinook.base.BaseEntite;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

/**
 *
 * @author cagecfi
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    @NotNull(message = "Nom non fourni.")
    @Size(min = 1, message = "Nom trop court.")
    private String firstName;
    @NotNull(message = "Prénom(s) non fourni(s).")
    @Size(min = 3, message = "Prénom(s) trop court.")
    private String lastName;
    private String title;
    private Integer reportsTo;
    @NotNull(message = "Date invalide.")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Temporal(TemporalType.DATE)
    private Date hireDate;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    @NotNull(message = "Adresse non fournie.")
    private Address address;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Artiste non fourni.")
    private Artist artist;
}
