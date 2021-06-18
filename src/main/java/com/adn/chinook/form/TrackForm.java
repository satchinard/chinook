package com.adn.chinook.form;

import com.adn.chinook.base.BaseForm;
import com.adn.chinook.entity.Track;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TrackForm extends BaseForm<Track, Integer> {

    private Integer trackId;
    private String name;
    private String composer;
    private Integer milliseconds;
    private Integer bytes;
    private Double unitPrice;
    private Integer genreId;
    private Integer mediaTypeId;
    private Integer albumId;

}
