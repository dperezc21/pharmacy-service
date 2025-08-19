package com.store.pharmacy_service.products.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "laboratory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Laboratory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;
}
