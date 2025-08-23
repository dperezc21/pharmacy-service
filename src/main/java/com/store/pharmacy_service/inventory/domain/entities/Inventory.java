package com.store.pharmacy_service.inventory.domain.entities;

import com.store.pharmacy_service.products.domain.entities.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Long quantity;

    private Date date;
}
