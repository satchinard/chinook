package com.adn.chinook.entity;

import com.adn.chinook.base.BaseEntite;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "CUSTOMER")
public class Customer extends BaseEntite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    @NotNull(message = "Prénom(s) non fournis.")
    @Size(min = 3, message = "Prénom(s) non valide.")
    private String firstName;
    @NotNull(message = "Nom(s) non fourni(s).")
    @Size(min = 3, message = "Nom(s) non valide.")
    private String lastName;
    @Size(min = 3, message = "Nom de compagnie trop court.")
    private String company;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    @NotNull(message = "Adresse non fournie.")
    private Address address;
}
