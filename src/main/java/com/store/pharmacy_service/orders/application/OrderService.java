package com.store.pharmacy_service.orders.application;

import com.store.pharmacy_service.inventory.application.InventoryService;
import com.store.pharmacy_service.inventory.utils.MapInventory;
import com.store.pharmacy_service.orders.domain.entities.Order;
import com.store.pharmacy_service.orders.domain.entities.OrderItem;
import com.store.pharmacy_service.orders.domain.models.OrderItemRequest;
import com.store.pharmacy_service.orders.domain.models.OrderRequest;
import com.store.pharmacy_service.orders.domain.models.OrderType;
import com.store.pharmacy_service.orders.domain.repositories.OrderRepository;
import com.store.pharmacy_service.products.application.ProductService;
import com.store.pharmacy_service.products.domain.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;

    @Autowired private ProductService productService;
    @Autowired private InventoryService inventoryService;

    public void placeOrder(OrderRequest orderRequest) {
        Order orderToSave = buildOrderToSave(orderRequest, OrderType.BUYS);
        Order save = this.orderRepository.save(orderToSave);
        this.inventoryService.saveProductsInInventory(MapInventory.mapInventoryRequest(save.getOrderItems()));
    }

    public void saleOrder(OrderRequest orderRequest) {
        Order order = buildOrderToSave(orderRequest, OrderType.SALE);
        Order save = this.orderRepository.save(order);
        this.inventoryService.saveProductsInInventoryFromSale(MapInventory.mapInventoryRequest(save.getOrderItems()));
    }

    private Order buildOrderToSave(OrderRequest orderRequest, OrderType orderType) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderItem> orderItems = orderRequest.getOrderItems().stream().map(orderItemRequest -> {
            Product product = this.productService.findProductById(orderItemRequest.getProductId());
            return this.mapToOrderItem(orderItemRequest, order, product);
        }).parallel().toList();
        order.setOrderItems(orderItems);
        order.setObservations(orderRequest.getObservations());
        order.setDate(orderRequest.getDate());
        order.setOrderType(orderType.toString());
        order.setTotal(orderRequest.getTotal());
        return order;
    }

    private OrderItem mapToOrderItem(OrderItemRequest orderItemRequest, Order order, Product product) {
        return OrderItem.builder()
                .product(product)
                .price(orderItemRequest.getUnitPrice())
                .quantity(orderItemRequest.getQuantity())
                .order(order)
                .subTotal(orderItemRequest.getSubTotal())
                .build();
    }
}
