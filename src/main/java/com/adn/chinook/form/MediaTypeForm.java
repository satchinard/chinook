package com.adn.chinook.form;

import com.adn.chinook.base.BaseForm;
import com.adn.chinook.entity.MediaTypeChinook;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MediaTypeForm extends BaseForm<MediaTypeChinook, Integer> {

    private Integer mediaTypeId;
    private String name;

}
