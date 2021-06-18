package com.adn.chinook.form;

import com.adn.chinook.base.BaseForm;
import com.adn.chinook.entity.Privilege;
import lombok.*;

import java.util.Collection;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PrivilegeForm extends BaseForm<Privilege, Long> {

    private Long privilegeId;
    private String name;
    private Collection<Long> roleIds;

}
