package com.adn.chinook.entity;

import com.adn.chinook.base.BaseEntite;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "MEDIATYPE")
public class MediaTypeChinook extends BaseEntite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mediaTypeId;
    @Column(unique = true)
    @NotNull(message = "Nom non fourni.")
    @Size(min = 2, message = "Taille du nom incorrrect (inférieur à 2).")
    private String name;

}
