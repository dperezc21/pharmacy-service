package com.store.pharmacy_service.products.domain.DTOs;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CategoryRequest {
    private Long categoryId;
    protected String name;
    protected String description;
}
