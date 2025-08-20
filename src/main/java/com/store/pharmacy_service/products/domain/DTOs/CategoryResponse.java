package com.store.pharmacy_service.products.domain.DTOs;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Long categoryId;
    private String name;
    private String description;
}
