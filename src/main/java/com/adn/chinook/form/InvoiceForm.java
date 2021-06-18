package com.adn.chinook.form;

import com.adn.chinook.base.BaseForm;
import com.adn.chinook.entity.Address;
import com.adn.chinook.entity.Invoice;
import java.util.Date;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InvoiceForm extends BaseForm<Invoice, Integer> {

    private Integer invoiceId;
    private Integer customerId;
    private Date invoiceDate;
    private Address billingAddress;
    private Double total;
}
