package com.adn.chinook.form;

import com.adn.chinook.base.BaseForm;
import com.adn.chinook.entity.Artist;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ArtistForm extends BaseForm<Artist, Integer> {

    private Integer artistId;
    private String name;

}
