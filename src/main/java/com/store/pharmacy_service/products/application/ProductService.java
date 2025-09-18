package com.store.pharmacy_service.products.application;

import com.store.pharmacy_service.inventory.domain.entities.Inventory;
import com.store.pharmacy_service.inventory.domain.repositories.InventoryRepository;
import com.store.pharmacy_service.products.domain.DTOs.ProductRequest;
import com.store.pharmacy_service.products.domain.DTOs.ProductResponse;
import com.store.pharmacy_service.products.domain.entities.PriceType;
import com.store.pharmacy_service.products.domain.entities.Product;
import com.store.pharmacy_service.products.domain.repositories.ProductRepository;
import com.store.pharmacy_service.products.utils.mappers.MapCategory;
import com.store.pharmacy_service.products.utils.mappers.MapLaboratory;
import com.store.pharmacy_service.products.utils.mappers.MapPriceType;
import com.store.pharmacy_service.products.utils.mappers.MapProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;
    @Autowired private InventoryRepository inventoryRepository;

    public ProductResponse saveProduct(ProductRequest productRequest) {

        Product productToSave = Product.builder()
                .sku(productRequest.getCode())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .laboratory(MapLaboratory.mapToLaboratory(productRequest.getLaboratory()))
                .category(MapCategory.mapToCategory(productRequest.getCategory()))
                .presentation(productRequest.getPresentation())
                .build();

        List<PriceType> priceTypes = productRequest.getPriceTypes().stream()
                .map(MapPriceType::mapToProductPriceType)
                .peek(priceType -> priceType.setProduct(productToSave))
                .parallel().toList();

        productToSave.setPriceTypes(priceTypes);
        Product result = productRepository.save(productToSave);
        if(Objects.nonNull(result.getId())) this.createInventoryOfProduct(result);
        return MapProduct.mapToProductResponse(result);
    }

    public ProductResponse editProduct(Long productId, ProductRequest productRequest) {

        Product findProductToEdit = this.productRepository.findById(productId).orElse(null);
        if(findProductToEdit == null) return null;
        Product productToSave = Product.builder()
                .id(findProductToEdit.getId())
                .sku(findProductToEdit.getSku())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .laboratory(MapLaboratory.mapToLaboratory(productRequest.getLaboratory()))
                .category(MapCategory.mapToCategory(productRequest.getCategory()))
                .presentation(productRequest.getPresentation())
                .build();

        List<PriceType> priceTypes = productRequest.getPriceTypes().stream()
                .map(MapPriceType::mapToProductPriceType)
                .peek(priceType -> priceType.setProduct(productToSave))
                .parallel().toList();

        productToSave.setPriceTypes(priceTypes);
        Product result = productRepository.save(productToSave);
        return MapProduct.mapToProductResponse(result);
    }

    public List<ProductResponse> getAllProducts() {
        return Streamable.of(this.productRepository.findAll())
                .stream().map(MapProduct::mapToProductResponse).parallel().toList();
    }

    public Boolean deleteProduct(Long productId) {
        Product productToDelete = this.productRepository.findById(productId).orElse(null);
        if(productToDelete == null) return false;
        this.productRepository.delete(productToDelete);
        return true;
    }

    public Product findProductById(Long productId) {
        return this.productRepository.findById(productId).orElse(null);
    }

    public void createInventoryOfProduct(Product productSaved) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(0L);
        inventory.setProduct(productSaved);
        this.inventoryRepository.save(inventory);
    }
}
