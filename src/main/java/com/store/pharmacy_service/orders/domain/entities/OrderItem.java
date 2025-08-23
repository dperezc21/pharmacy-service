package com.store.pharmacy_service.orders.domain.entities;

import com.store.pharmacy_service.products.domain.entities.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Double price;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
