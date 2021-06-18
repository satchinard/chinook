package com.adn.chinook.form;

import com.adn.chinook.base.BaseForm;
import com.adn.chinook.entity.Role;
import lombok.*;

import java.util.Collection;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleForm extends BaseForm<Role, Long> {

    private Long roleId;
    private String name;
    private Collection<Long> userIds;
    private Collection<Long> privilegeIds;

}
