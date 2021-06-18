package com.adn.chinook.entity;

import com.adn.chinook.base.BaseEntite;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
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
@Table(name = "TRACK")
public class Track extends BaseEntite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trackId;
    @Column(unique = true)
    @NotNull(message = "Nom non fourni.")
    @Size(min = 2, message = "Taille du nom incorrect.")
    private String name;
    @NotNull(message = "Compositeur non fourni.")
    @Size(min = 2, message = "Taille du compositeur incorrect.")
    private String composer;
    @Min(value = 1, message = "Durée inférieure à 1 milliseconde.")
    private Integer milliseconds;
    @Min(value = 1, message = "Taille inférieure à 1 bytes.")
    private Integer bytes;
    private Double unitPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Genre non fourni.")
    private Genre genre;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Type de média non fourni.")
    private MediaTypeChinook mediaType;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Album non fourni.")
    private Album album;

}
