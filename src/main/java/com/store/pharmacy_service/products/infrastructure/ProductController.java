package com.store.pharmacy_service.products.infrastructure;

import com.store.pharmacy_service.products.application.ProductService;
import com.store.pharmacy_service.products.domain.DTOs.ProductRequest;
import com.store.pharmacy_service.products.domain.DTOs.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired private ProductService productService;

    @PostMapping
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest) {
        return this.productService.saveProduct(productRequest);
    }

    @GetMapping
    public List<ProductResponse> getProductList() {
        return this.productService.getAllProducts();
    }

    @PutMapping("/{productId}")
    public ProductResponse update(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        return this.productService.editProduct(productId, productRequest);
    }

    @DeleteMapping("/{productId}")
    public Boolean deleteProduct(@PathVariable Long productId) {
        return this.productService.deleteProduct(productId);
    }
}
