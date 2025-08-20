package com.store.pharmacy_service.products.domain.DTOs;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CategoryResponse extends BaseDto {
    private Long categoryId;
}
