package com.store.pharmacy_service.orders.application;

import com.store.pharmacy_service.inventory.application.InventoryService;
import com.store.pharmacy_service.inventory.utils.MapInventory;
import com.store.pharmacy_service.orders.domain.entities.OrderItem;
import com.store.pharmacy_service.orders.domain.models.OrderItemRequest;
import com.store.pharmacy_service.orders.domain.models.OrderRequest;
import com.store.pharmacy_service.orders.domain.models.OrderType;
import com.store.pharmacy_service.orders.domain.repositories.OrderItemRepository;
import com.store.pharmacy_service.products.application.ProductService;
import com.store.pharmacy_service.products.domain.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired private OrderItemRepository orderItemRepository;

    @Autowired private ProductService productService;
    @Autowired private InventoryService inventoryService;

    public void placeOrder(OrderRequest orderRequest) {
        List<OrderItem> orderToSave = buildOrderToSave(orderRequest, OrderType.BUYS);
        this.inventoryService.saveProductsInInventory(MapInventory.mapInventoryRequest(orderToSave));
    }

    public void saleOrder(OrderRequest orderRequest) {
        List<OrderItem> order = buildOrderToSave(orderRequest, OrderType.SALE);
        this.inventoryService.saveProductsInInventoryFromSale(MapInventory.mapInventoryRequest(order));
    }

    public List<OrderItem> buildOrderToSave(OrderRequest orderRequest, OrderType orderType) {

        List<OrderItem> orderItems = orderRequest.getOrderItems().stream().map(orderItemRequest -> {
            Product product = this.productService.findProductById(orderItemRequest.getProductId());
            OrderItem orderItem = this.mapToOrderItem(orderItemRequest, product);
            orderItem.setOrderType(orderType.name());
            orderItem.setDate(orderRequest.getDate());
            return orderItem;
        }).toList();
        return Streamable.of(this.orderItemRepository.saveAll(orderItems)).toList();
    }

    private OrderItem mapToOrderItem(OrderItemRequest orderItemRequest, Product product) {
        return OrderItem.builder()
                .product(product)
                .price(orderItemRequest.getUnitPrice())
                .quantity(orderItemRequest.getQuantity())
                .subTotal(orderItemRequest.getSubTotal())
                .priceTypeName(orderItemRequest.getPriceTypeName())
                .totalQuantity(orderItemRequest.getTotalQuantity())
                .build();
    }
}
