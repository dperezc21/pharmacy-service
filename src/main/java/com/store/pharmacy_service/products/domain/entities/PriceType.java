package com.store.pharmacy_service.products.domain.entities;

import lombok.*;

@Getter
@NoArgsConstructor
public enum PriceType {
    UNIT("Precio por unidad"),
    PACKAGE("Precio por paquete"),
    BOX("Precio por caja"),
    BLISTER("Precio por blister");

    private String label;

    PriceType(String label) {
        this.label = label;
    }
}
