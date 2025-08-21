package com.store.pharmacy_service.products.utils.mappers;

import com.store.pharmacy_service.products.domain.DTOs.LaboratoryRequest;
import com.store.pharmacy_service.products.domain.DTOs.LaboratoryResponse;
import com.store.pharmacy_service.products.domain.entities.Laboratory;

public class MapLaboratory {

    public static LaboratoryResponse mapToLaboratoryResponse(Laboratory laboratory) {
        return LaboratoryResponse.builder()
                .description(laboratory.getDescription())
                .laboratoryId(laboratory.getId())
                .name(laboratory.getName()).build();
    }

    public static Laboratory mapToLaboratory(LaboratoryRequest laboratoryRequest) {
        return Laboratory.builder()
                .description(laboratoryRequest.getDescription())
                .name(laboratoryRequest.getName())
                .id(laboratoryRequest.getLaboratoryId()).build();
    }
}
