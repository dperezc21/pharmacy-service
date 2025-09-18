package com.store.pharmacy_service.products.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "price_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String label;
    private Double price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
