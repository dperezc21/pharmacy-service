package com.store.pharmacy_service.products.infrastructure;

import com.store.pharmacy_service.products.application.LaboratoryService;
import com.store.pharmacy_service.products.domain.DTOs.LaboratoryRequest;
import com.store.pharmacy_service.products.domain.DTOs.LaboratoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laboratories")
public class LaboratoryController {

    @Autowired private LaboratoryService repository;

    @GetMapping
    public List<LaboratoryResponse> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    @PostMapping
    public LaboratoryResponse create(@RequestBody LaboratoryRequest lab) {
        return repository.saveLaboratory(lab);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<LaboratoryResponse> update(@PathVariable Long id, @RequestBody LaboratoryRequest lab) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(lab.getName());
                    existing.setDescription(lab.getDescription());
                    return ResponseEntity.ok(repository.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repository.findById(id).map(lab -> {
            repository.delete(lab);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }*/
}
