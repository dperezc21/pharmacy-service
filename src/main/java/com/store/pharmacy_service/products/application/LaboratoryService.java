package com.store.pharmacy_service.products.application;

import com.store.pharmacy_service.products.domain.DTOs.LaboratoryRequest;
import com.store.pharmacy_service.products.domain.DTOs.LaboratoryResponse;
import com.store.pharmacy_service.products.domain.entities.Laboratory;
import com.store.pharmacy_service.products.domain.repositories.LaboratoryRepository;
import com.store.pharmacy_service.products.utils.mappers.MapLaboratory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LaboratoryService {
    @Autowired private LaboratoryRepository laboratoryRepository;

    public List<LaboratoryResponse> findAll() {
        List<Laboratory> laboratories = Streamable.of(this.laboratoryRepository.findAll()).toList();
        return laboratories.stream().map(MapLaboratory::mapToLaboratoryResponse).toList();
    }

    public LaboratoryResponse findById(Long id) {
        Laboratory laboratory = this.laboratoryRepository.findById(id).orElse(null);
        if(laboratory == null) return null;
        return MapLaboratory.mapToLaboratoryResponse(laboratory);
    }

    public LaboratoryResponse saveLaboratory(LaboratoryRequest lab) {
        Laboratory laboratory = Laboratory.builder()
                .name(lab.getName())
                .description(lab.getDescription())
                .build();
        Laboratory laboratorySaved = this.laboratoryRepository.save(laboratory);
        return Objects.nonNull(laboratorySaved.getId()) ? MapLaboratory.mapToLaboratoryResponse(laboratorySaved) : null;
    }
}
