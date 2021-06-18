package com.adn.chinook.entity;

import com.adn.chinook.base.BaseEntite;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "ALBUM")
public class Album extends BaseEntite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer albumId;
    @NotNull(message = "Titre non fourni.")
    @Size(min = 3, message = "Titre non valide.")
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Artiste non fourni.")
    private Artist artist;
}
