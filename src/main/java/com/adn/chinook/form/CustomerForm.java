package com.adn.chinook.form;

import com.adn.chinook.base.BaseForm;
import com.adn.chinook.entity.Customer;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomerForm extends BaseForm<Customer, Integer> {

    private Integer customerId;
    private String firstName;
    private String lastName;
    private String company;
    private Integer addressId;

}
