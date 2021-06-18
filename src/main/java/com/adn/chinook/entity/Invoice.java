package com.adn.chinook.entity;

import com.adn.chinook.base.BaseEntite;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
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
@Table(name = "INVOICE")
public class Invoice extends BaseEntite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceId;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Client non fourni.")
    private Customer customer;
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;
    @Transient
    private Double total;

}
