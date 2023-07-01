package com.smumo.inventorysystem.entities;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private BigDecimal price;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;
}
