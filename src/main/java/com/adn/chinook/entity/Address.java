package com.adn.chinook.entity;

import com.adn.chinook.base.BaseEntite;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ADDRESS")
public class Address extends BaseEntite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;
    @NotNull(message = "Adresse non fournie.")
    @Size(min = 5, message = "Adresse non valide.")
//    @Convert(converter = AttributeEncryptor.class)
    private String adresse;
    @NotNull(message = "Ville non fournie.")
    @Size(min = 3, message = "Ville non valide.")
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String phone;
    private String fax;
    @Email(message = "Email non valide.")
    @NotNull(message = "Email non fourni.")
    @NotBlank(message = "Email non fourni.")
    @Size(min = 4, message = "Email non valide.")
    private String email;

    public Address(Integer addressId) {
        this.addressId = addressId;
    }

}
