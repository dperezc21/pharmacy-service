package com.store.pharmacy_service.products.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String sku; // Stock Keeping Unit

    @Column(unique = true)
    private String name;
    private String description;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "package_sale_price")
    private Double packageSalePrice;

    @Column(name = "package_unit")
    private Integer packageUnit;

    private String presentation;

    @ManyToOne
    @JoinColumn(name = "laboratory_id")
    private Laboratory laboratory;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
