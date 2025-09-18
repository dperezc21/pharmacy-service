package com.store.pharmacy_service.products.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    private String presentation;

    @ManyToOne
    @JoinColumn(name = "laboratory_id")
    private Laboratory laboratory;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private List<PriceType> priceTypes;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", presentation='" + presentation + '\'' +
                ", laboratory=" + laboratory +
                ", category=" + category +
                ", priceTypes=" + priceTypes +
                '}';
    }
}
