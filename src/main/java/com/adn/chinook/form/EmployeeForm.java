package com.adn.chinook.form;

import com.adn.chinook.base.BaseForm;
import com.adn.chinook.entity.Employee;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeForm extends BaseForm<Employee, Integer> {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String title;
    private Integer reportsTo;
    private Date birthDate;
    private Date hireDate;
    private Integer addressId;
    private Integer artistId;

}
